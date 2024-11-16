package br.com.vr.beneficios.autorizador.service;

import br.com.vr.beneficios.autorizador.dto.CartaoRequest;
import br.com.vr.beneficios.autorizador.dto.CartaoResponse;
import br.com.vr.beneficios.autorizador.mapper.CartaoMapper;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import br.com.vr.beneficios.autorizador.repository.cartao.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CartaoService cartaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarCartaoComSucesso() {
        // Dados de entrada
        Cartao cartao = new Cartao("123456789", "1234");
        cartao.setSaldo(BigDecimal.valueOf(500.00));

        // Configura o mock
        when(cartaoRepository.findByNumeroCartao("123456789")).thenReturn(Optional.of(cartao));

        // Executa o teste
        Optional<Cartao> resultado = cartaoService.buscarCartao("123456789");

        // Verificações
        assertTrue(resultado.isPresent());
        assertEquals("123456789", resultado.get().getNumeroCartao());
        assertEquals(BigDecimal.valueOf(500.00), resultado.get().getSaldo());
    }

    @Test
    void deveCriarCartaoComSaldoInicial() {
        // Dados de entrada
        CartaoRequest request = new CartaoRequest("123456789", "1234");
        Cartao cartao = new Cartao("123456789", "1234");
        Cartao cartaoCriado = new Cartao("123456789", "1234");
        cartaoCriado.setSaldo(BigDecimal.valueOf(500.00));

        // Configura o mock
        when(cartaoRepository.save(any(Cartao.class))).thenReturn(cartaoCriado);

        // Executa o teste
        CartaoResponse response = cartaoService.criarCartao(request);

        // Verificações
        assertEquals("123456789", response.getNumeroCartao());
        assertEquals(500.00, response.getSaldo().doubleValue());
        verify(cartaoRepository, times(1)).save(any(Cartao.class));
    }

    @Test
    void deveObterSaldoDoCartaoComSucesso() {
        // Dados de entrada
        Cartao cartao = new Cartao("123456789", "1234");
        cartao.setSaldo(BigDecimal.valueOf(500.00));
        CartaoResponse expectedResponse = CartaoMapper.toResponse(cartao);

        // Configura o mock
        when(cartaoRepository.findByNumeroCartao("123456789")).thenReturn(Optional.of(cartao));

        // Executa o teste
        Optional<CartaoResponse> resultado = cartaoService.obterSaldo("123456789");

        // Verificações
        assertTrue(resultado.isPresent());
        assertEquals(expectedResponse.getNumeroCartao(), resultado.get().getNumeroCartao());
        assertEquals(expectedResponse.getSaldo(), resultado.get().getSaldo());
    }

    @Test
    void deveRetornarVazioQuandoCartaoNaoEncontrado() {
        // Configura o mock
        when(cartaoRepository.findByNumeroCartao("000000000")).thenReturn(Optional.empty());

        // Executa o teste
        Optional<CartaoResponse> resultado = cartaoService.obterSaldo("000000000");

        // Verificações
        assertTrue(resultado.isEmpty());
    }
}
