package br.com.vr.beneficios.autorizador.mapper;

import br.com.vr.beneficios.autorizador.dto.TransacaoResponse;
import br.com.vr.beneficios.autorizador.model.transacao.Transacao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoMapperTest {

    @Test
    void deveMapearTransacaoParaResponse() {
        // Dado
        Transacao transacao = new Transacao("1234567890123456", BigDecimal.valueOf(10), "OK");

        // Quando
        TransacaoResponse response = TransacaoMapper.INSTANCE.toResponse(transacao);

        // Então
        assertNotNull(response);
        assertEquals(transacao.getStatus(), response.getStatus());
    }
}
