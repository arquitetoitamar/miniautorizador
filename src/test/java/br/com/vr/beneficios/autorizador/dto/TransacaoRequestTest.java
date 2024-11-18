package br.com.vr.beneficios.autorizador.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoRequestTest {

    @Test
    void deveSerIgualQuandoTodosOsCamposSaoIguais() {
        TransacaoRequest request1 = new TransacaoRequest("1234567890123456", "1234", BigDecimal.valueOf(100.00));
        TransacaoRequest request2 = new TransacaoRequest("1234567890123456", "1234", BigDecimal.valueOf(100.00));

        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
    }

    @Test
    void naoDeveSerIgualQuandoValorDiferente() {
        TransacaoRequest request1 = new TransacaoRequest("1234567890123456", "1234", BigDecimal.valueOf(100.00));
        TransacaoRequest request2 = new TransacaoRequest("1234567890123456", "1234", BigDecimal.valueOf(200.00));

        assertNotEquals(request1, request2);
        assertNotEquals(request1.hashCode(), request2.hashCode());
    }


}
