package br.com.vr.beneficios.autorizador.strategy;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegraAutorizacaoTest {

    @Mock
    private RegraAutorizacao regraAutorizacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarOkQuandoValidacaoPassar() {
        // Dados de entrada
        Cartao cartao = new Cartao("123456789", "1234");
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", BigDecimal.valueOf(100.00));

        // Configuração do mock
        when(regraAutorizacao.validar(any(Cartao.class), any(TransacaoRequest.class))).thenReturn("OK");

        // Execução do teste
        String resultado = regraAutorizacao.validar(cartao, request);

        // Verificação
        assertEquals("OK", resultado);
        verify(regraAutorizacao, times(1)).validar(cartao, request);
    }

    @Test
    void deveRetornarErroQuandoValidacaoFalhar() {
        // Dados de entrada
        Cartao cartao = new Cartao("123456789", "1234");
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", BigDecimal.valueOf(100.00));

        // Configuração do mock
        when(regraAutorizacao.validar(any(Cartao.class), any(TransacaoRequest.class))).thenReturn("SENHA_INVALIDA");

        // Execução do teste
        String resultado = regraAutorizacao.validar(cartao, request);

        // Verificação
        assertEquals("SENHA_INVALIDA", resultado);
        verify(regraAutorizacao, times(1)).validar(cartao, request);
    }
}
