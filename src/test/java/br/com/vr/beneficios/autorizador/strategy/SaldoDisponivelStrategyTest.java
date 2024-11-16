package br.com.vr.beneficios.autorizador.strategy;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaldoDisponivelStrategyTest {

    private SaldoDisponivelStrategy saldoDisponivelStrategy;

    @BeforeEach
    void setUp() {
        saldoDisponivelStrategy = new SaldoDisponivelStrategy();
    }

    @Test
    void deveRetornarOkQuandoSaldoForSuficiente() {
        // Dados de entrada
        Cartao cartao = new Cartao("123456789", "1234");
        cartao.setSaldo(BigDecimal.valueOf(500.00));
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", BigDecimal.valueOf(100.00));

        // Executa o teste
        String resultado = saldoDisponivelStrategy.validar(cartao, request);

        // Verificação
        assertEquals("OK", resultado);
    }

    @Test
    void deveRetornarSaldoInsuficienteQuandoSaldoForMenorQueValorTransacao() {
        // Dados de entrada
        Cartao cartao = new Cartao("123456789", "1234");
        cartao.setSaldo(BigDecimal.valueOf(50.00));
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", BigDecimal.valueOf(100.00));

        // Executa o teste
        String resultado = saldoDisponivelStrategy.validar(cartao, request);

        // Verificação
        assertEquals("SALDO_INSUFICIENTE", resultado);
    }

    @Test
    void deveRetornarOkQuandoSaldoForIgualAoValorTransacao() {
        // Dados de entrada
        Cartao cartao = new Cartao("123456789", "1234");
        cartao.setSaldo(BigDecimal.valueOf(100.00));
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", BigDecimal.valueOf(100.00));

        // Executa o teste
        String resultado = saldoDisponivelStrategy.validar(cartao, request);

        // Verificação
        assertEquals("OK", resultado);
    }
}
