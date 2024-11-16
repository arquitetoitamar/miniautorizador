package br.com.vr.beneficios.autorizador.controller;

import br.com.vr.beneficios.autorizador.dto.CartaoRequest;
import br.com.vr.beneficios.autorizador.dto.CartaoResponse;
import br.com.vr.beneficios.autorizador.service.CartaoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CartaoControllerTest {

    @Mock
    private CartaoService cartaoService;

    @InjectMocks
    private CartaoController cartaoController;

    public CartaoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarCartaoComSucesso() {
        CartaoRequest request = new CartaoRequest("123456789", "1234");
        CartaoResponse response = new CartaoResponse("123456789", BigDecimal.valueOf(500));

        when(cartaoService.criarCartao(any())).thenReturn(response);

        ResponseEntity<CartaoResponse> result = cartaoController.criarCartao(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
