package br.com.vr.beneficios.autorizador.service;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.dto.TransacaoResponse;
import br.com.vr.beneficios.autorizador.mapper.TransacaoMapper;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import br.com.vr.beneficios.autorizador.model.transacao.Transacao;
import br.com.vr.beneficios.autorizador.repository.cartao.CartaoRepository;
import br.com.vr.beneficios.autorizador.repository.TransacaoRepository;
import br.com.vr.beneficios.autorizador.strategy.RegraAutorizacao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorizacaoService {

    private final CartaoRepository cartaoRepository;
    private final TransacaoRepository transacaoRepository;
    private final List<RegraAutorizacao> regrasAutorizacao;

    public AutorizacaoService(CartaoRepository cartaoRepository,
                              TransacaoRepository transacaoRepository,
                              List<RegraAutorizacao> regrasAutorizacao) {
        this.cartaoRepository = cartaoRepository;
        this.transacaoRepository = transacaoRepository;
        this.regrasAutorizacao = regrasAutorizacao;
    }

    @Transactional
    public TransacaoResponse validarTransacao(TransacaoRequest request) {
        // Busca o cartão pelo número usando o JPA Repository
        Optional<Cartao> optionalCartao = cartaoRepository.findByNumeroCartao(request.getNumeroCartao());

        if (optionalCartao.isEmpty()) {
            Transacao transacaoFalha = new Transacao(request.getNumeroCartao(), request.getValor(), "CARTAO_INEXISTENTE");
            transacaoRepository.save(transacaoFalha);
            return TransacaoMapper.toResponse(transacaoFalha);
        }

        Cartao cartao = optionalCartao.get();

        // Valida todas as regras de autorização
        for (RegraAutorizacao regra : regrasAutorizacao) {
            String resultado = regra.validar(cartao, request);
            if (!"OK".equals(resultado)) {
                Transacao transacaoFalha = new Transacao(request.getNumeroCartao(), request.getValor(), resultado);
                transacaoRepository.save(transacaoFalha);
                return TransacaoMapper.toResponse(transacaoFalha);
            }
        }

        // Atualiza o saldo do cartão e salva a transação
        cartao.setSaldo(cartao.getSaldo().subtract(request.getValor()));
        cartaoRepository.save(cartao);

        Transacao transacao = new Transacao(request.getNumeroCartao(), request.getValor(), "OK");
        transacaoRepository.save(transacao);

        return TransacaoMapper.toResponse(transacao);
    }
}
