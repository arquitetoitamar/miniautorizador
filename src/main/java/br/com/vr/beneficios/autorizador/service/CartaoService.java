package br.com.vr.beneficios.autorizador.service;

import br.com.vr.beneficios.autorizador.dto.CartaoRequest;
import br.com.vr.beneficios.autorizador.dto.CartaoResponse;
import br.com.vr.beneficios.autorizador.mapper.CartaoMapper;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import br.com.vr.beneficios.autorizador.repository.cartao.CartaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartaoService {

    private static final BigDecimal SALDO_INICIAL = BigDecimal.valueOf(500.00);
    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    /**
     * Busca um cartão pelo número.
     *
     * @param numeroCartao Número do cartão.
     * @return Um Optional contendo o Cartao, se encontrado.
     */
    public Optional<Cartao> buscarCartao(String numeroCartao) {
        return cartaoRepository.findByNumeroCartao(numeroCartao);
    }

    /**
     * Cria um novo cartão com saldo inicial de R$500,00.
     *
     * @param request Dados do CartaoRequest.
     * @return CartaoResponse com os dados do cartão criado.
     */
    public CartaoResponse criarCartao(CartaoRequest request) {
        // Converte o CartaoRequest para a entidade Cartao usando o Mapper
        Cartao cartao = CartaoMapper.INSTANCE.toEntity(request);
        cartao.setSaldo(SALDO_INICIAL);

        // Salva o cartão no banco de dados e retorna a resposta mapeada
        Cartao cartaoCriado = cartaoRepository.save(cartao);
        return CartaoMapper.INSTANCE.toResponse(cartaoCriado);
    }

    /**
     * Obtém o saldo de um cartão.
     *
     * @param numeroCartao Número do cartão.
     * @return Um Optional contendo o CartaoResponse com o saldo, se o cartão existir.
     */
    public Optional<CartaoResponse> obterSaldo(String numeroCartao) {
        // Busca o cartão e mapeia para o DTO de resposta
        return cartaoRepository.findByNumeroCartao(numeroCartao)
                .map(CartaoMapper.INSTANCE::toResponse);
    }
}
