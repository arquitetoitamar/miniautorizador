package br.com.vr.beneficios.autorizador.mapper;

import br.com.vr.beneficios.autorizador.dto.CartaoRequest;
import br.com.vr.beneficios.autorizador.dto.CartaoResponse;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartaoMapperTest {

    @Test
    void deveMapearCartaoRequestParaEntity() {
        // Dado
        CartaoRequest request = new CartaoRequest("1234567890123456", "1234");

        // Quando
        Cartao cartao = CartaoMapper.INSTANCE.toEntity(request);

        // Então
        assertNotNull(cartao);
        assertEquals(request.getNumeroCartao(), cartao.getNumeroCartao());
        assertEquals(request.getSenha(), cartao.getSenha());
    }

    @Test
    void deveMapearCartaoParaResponse() {
        // Dado
        Cartao cartao = new Cartao("1234567890123456", "1234");
        cartao.setSaldo(BigDecimal.valueOf(500));

        // Quando
        CartaoResponse response = CartaoMapper.INSTANCE.toResponse(cartao);

        // Então
        assertNotNull(response);
        assertEquals(cartao.getNumeroCartao(), response.getNumeroCartao());
        assertEquals(cartao.getSaldo(), response.getSaldo());
    }
}
