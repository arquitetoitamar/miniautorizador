package br.com.vr.beneficios.autorizador.strategy;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartaoExistenteStrategyTest {

    private CartaoExistenteStrategy cartaoExistenteStrategy;

    @BeforeEach
    void setUp() {
        cartaoExistenteStrategy = new CartaoExistenteStrategy();
    }

    @Test
    void deveRetornarCartaoInexistenteQuandoCartaoForNulo() {
        // Dados de entrada
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", null);

        // Executa o teste
        String resultado = cartaoExistenteStrategy.validar(null, request);

        // Verificação
        assertEquals("CARTAO_INEXISTENTE", resultado);
    }

    @Test
    void deveRetornarOkQuandoCartaoExistir() {
        // Dados de entrada
        Cartao cartao = new Cartao("123456789", "1234");
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", null);

        // Executa o teste
        String resultado = cartaoExistenteStrategy.validar(cartao, request);

        // Verificação
        assertEquals("OK", resultado);
    }
}
