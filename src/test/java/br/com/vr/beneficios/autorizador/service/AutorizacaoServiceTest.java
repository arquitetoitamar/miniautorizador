package br.com.vr.beneficios.autorizador.service;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.dto.TransacaoResponse;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import br.com.vr.beneficios.autorizador.model.transacao.Transacao;
import br.com.vr.beneficios.autorizador.repository.cartao.CartaoRepository;
import br.com.vr.beneficios.autorizador.repository.TransacaoRepository;
import br.com.vr.beneficios.autorizador.strategy.RegraAutorizacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AutorizacaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private RegraAutorizacao regraCartaoExistente;

    @Mock
    private RegraAutorizacao regraSenhaValida;

    @Mock
    private RegraAutorizacao regraSaldoDisponivel;

    @InjectMocks
    private AutorizacaoService autorizacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configura a lista de regras de autorização
        autorizacaoService = new AutorizacaoService(
                cartaoRepository,
                transacaoRepository,
                List.of(regraCartaoExistente, regraSenhaValida, regraSaldoDisponivel)
        );
    }

    @Test
    void deveAutorizarTransacaoComSucesso() {
        Cartao cartao = new Cartao("123456789", "1234");
        cartao.setSaldo(BigDecimal.valueOf(500.00));
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", BigDecimal.valueOf(100.00));
        TransacaoResponse expectedResponse = new TransacaoResponse("OK");

        when(cartaoRepository.findByNumeroCartao("123456789")).thenReturn(Optional.of(cartao));
        when(regraCartaoExistente.validar(cartao, request)).thenReturn("OK");
        when(regraSenhaValida.validar(cartao, request)).thenReturn("OK");
        when(regraSaldoDisponivel.validar(cartao, request)).thenReturn("OK");

        TransacaoResponse response = autorizacaoService.validarTransacao(request);

        assertEquals(expectedResponse.getStatus(), response.getStatus());
        verify(cartaoRepository, times(1)).save(cartao);
        verify(transacaoRepository, times(1)).save(any(Transacao.class));
    }

    @Test
    void deveNegarTransacaoPorSaldoInsuficiente() {
        Cartao cartao = new Cartao("123456789", "1234");
        cartao.setSaldo(BigDecimal.valueOf(50.00));
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", BigDecimal.valueOf(100.00));

        when(cartaoRepository.findByNumeroCartao("123456789")).thenReturn(Optional.of(cartao));
        when(regraCartaoExistente.validar(cartao, request)).thenReturn("OK");
        when(regraSenhaValida.validar(cartao, request)).thenReturn("OK");
        when(regraSaldoDisponivel.validar(cartao, request)).thenReturn("SALDO_INSUFICIENTE");

        TransacaoResponse response = autorizacaoService.validarTransacao(request);

        assertEquals("SALDO_INSUFICIENTE", response.getStatus());
        verify(transacaoRepository, times(1)).save(any(Transacao.class));
        verify(cartaoRepository, never()).save(any(Cartao.class));
    }
}
