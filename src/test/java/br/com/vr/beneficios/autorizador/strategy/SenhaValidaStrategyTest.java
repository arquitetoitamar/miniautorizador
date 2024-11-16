package br.com.vr.beneficios.autorizador.strategy;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SenhaValidaStrategyTest {

    private SenhaValidaStrategy senhaValidaStrategy;

    @BeforeEach
    void setUp() {
        senhaValidaStrategy = new SenhaValidaStrategy();
    }

    @Test
    void deveRetornarOkQuandoSenhaForValida() {
        // Dados de entrada
        Cartao cartao = new Cartao("123456789", "1234");
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", null);

        // Executa o teste
        String resultado = senhaValidaStrategy.validar(cartao, request);

        // Verificação
        assertEquals("OK", resultado);
    }

    @Test
    void deveRetornarSenhaInvalidaQuandoSenhaNaoCorresponder() {
        // Dados de entrada
        Cartao cartao = new Cartao("123456789", "1234");
        TransacaoRequest request = new TransacaoRequest("123456789", "9999", null);

        // Executa o teste
        String resultado = senhaValidaStrategy.validar(cartao, request);

        // Verificação
        assertEquals("SENHA_INVALIDA", resultado);
    }
}
