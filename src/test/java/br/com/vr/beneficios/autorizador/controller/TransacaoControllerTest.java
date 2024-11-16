package br.com.vr.beneficios.autorizador.controller;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.dto.TransacaoResponse;
import br.com.vr.beneficios.autorizador.service.AutorizacaoService;
import org.junit.jupiter.api.BeforeEach;
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

class TransacaoControllerTest {

    @Mock
    private AutorizacaoService autorizacaoService;

    @InjectMocks
    private TransacaoController transacaoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAutorizarTransacaoComSucesso() {
        // Dados de entrada
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", BigDecimal.valueOf(100.00));
        TransacaoResponse response = new TransacaoResponse("OK");

        // Configura o comportamento do mock
        when(autorizacaoService.validarTransacao(any(TransacaoRequest.class))).thenReturn(response);

        // Executa o teste
        ResponseEntity<TransacaoResponse> resultado = transacaoController.realizarTransacao(request);

        // Verificações
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals("OK", resultado.getBody().getStatus());
    }

    @Test
    void deveNegarTransacaoPorSaldoInsuficiente() {
        // Dados de entrada
        TransacaoRequest request = new TransacaoRequest("123456789", "1234", BigDecimal.valueOf(200.00));
        TransacaoResponse response = new TransacaoResponse("SALDO_INSUFICIENTE");

        // Configura o comportamento do mock
        when(autorizacaoService.validarTransacao(any(TransacaoRequest.class))).thenReturn(response);

        // Executa o teste
        ResponseEntity<TransacaoResponse> resultado = transacaoController.realizarTransacao(request);

        // Verificações
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, resultado.getStatusCode());
        assertEquals("SALDO_INSUFICIENTE", resultado.getBody().getStatus());
    }

    @Test
    void deveNegarTransacaoPorSenhaInvalida() {
        // Dados de entrada
        TransacaoRequest request = new TransacaoRequest("123456789", "9999", BigDecimal.valueOf(50.00));
        TransacaoResponse response = new TransacaoResponse("SENHA_INVALIDA");

        // Configura o comportamento do mock
        when(autorizacaoService.validarTransacao(any(TransacaoRequest.class))).thenReturn(response);

        // Executa o teste
        ResponseEntity<TransacaoResponse> resultado = transacaoController.realizarTransacao(request);

        // Verificações
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, resultado.getStatusCode());
        assertEquals("SENHA_INVALIDA", resultado.getBody().getStatus());
    }

    @Test
    void deveNegarTransacaoPorCartaoInexistente() {
        // Dados de entrada
        TransacaoRequest request = new TransacaoRequest("000000000", "1234", BigDecimal.valueOf(100.00));
        TransacaoResponse response = new TransacaoResponse("CARTAO_INEXISTENTE");

        // Configura o comportamento do mock
        when(autorizacaoService.validarTransacao(any(TransacaoRequest.class))).thenReturn(response);

        // Executa o teste
        ResponseEntity<TransacaoResponse> resultado = transacaoController.realizarTransacao(request);

        // Verificações
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, resultado.getStatusCode());
        assertEquals("CARTAO_INEXISTENTE", resultado.getBody().getStatus());
    }
}
