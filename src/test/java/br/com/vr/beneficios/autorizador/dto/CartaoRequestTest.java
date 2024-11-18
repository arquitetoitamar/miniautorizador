package br.com.vr.beneficios.autorizador.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartaoRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveRetornarErroQuandoNumeroCartaoEstiverVazio() {
        CartaoRequest request = new CartaoRequest("", "1234");

        Set<ConstraintViolation<CartaoRequest>> violations = validator.validate(request);

        // Verifique se existem 2 violações: NotBlank e Pattern
        assertEquals(3, violations.size());

        // Verifique se a mensagem "O número do cartão não pode estar em branco." está presente
        boolean mensagemNumeroCartaoVazio = violations.stream()
                .anyMatch(v -> v.getMessage().equals("O número do cartão não pode estar em branco."));

        assertTrue(mensagemNumeroCartaoVazio, "A mensagem de erro esperada para número de cartão vazio não foi encontrada.");
    }

    @Test
    void deveRetornarErroQuandoNumeroCartaoNaoTiver16Digitos() {
        CartaoRequest request = new CartaoRequest("123456789", "1234");

        Set<ConstraintViolation<CartaoRequest>> violations = validator.validate(request);

        // Espera-se 1 violação para o tamanho incorreto do número do cartão.
        assertEquals(2, violations.size());

        // Verifica se a mensagem "O número do cartão deve ter 16 dígitos." está presente
        boolean mensagemErroEncontrada = violations.stream()
                .anyMatch(v -> v.getMessage().equals("O número do cartão deve ter 16 dígitos."));

        assertTrue(mensagemErroEncontrada, "A mensagem de erro esperada para número do cartão com tamanho incorreto não foi encontrada.");
    }

    @Test
    void deveRetornarErroQuandoNumeroCartaoContiverCaracteresInvalidos() {
        CartaoRequest request = new CartaoRequest("1234abcd5678efgh", "1234");

        Set<ConstraintViolation<CartaoRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("O número do cartão deve conter apenas dígitos."));
    }

    @Test
    void deveRetornarErroQuandoSenhaEstiverVazia() {
        CartaoRequest request = new CartaoRequest("1234567890123456", "");

        Set<ConstraintViolation<CartaoRequest>> violations = validator.validate(request);

        assertEquals(3, violations.size());
        // Verifique se a mensagem "A senha não pode estar em branco." está presente
        boolean mensagemEncontrada = violations.stream()
                .anyMatch(v -> v.getMessage().equals("A senha não pode estar em branco."));

        assertTrue(mensagemEncontrada, "A mensagem de erro esperada não foi encontrada.");
    }
    @Test
    void deveRetornarErroQuandoSenhaNaoTiver4Digitos() {
        CartaoRequest request = new CartaoRequest("1234567890123456", "12");

        Set<ConstraintViolation<CartaoRequest>> violations = validator.validate(request);

        // Espera-se 1 violação para o tamanho incorreto da senha.
        assertEquals(2, violations.size());

        // Verifica se a mensagem "A senha deve ter 4 dígitos." está presente
        boolean mensagemErroEncontrada = violations.stream()
                .anyMatch(v -> v.getMessage().equals("A senha deve ter 4 dígitos."));

        assertTrue(mensagemErroEncontrada, "A mensagem de erro esperada para senha com tamanho incorreto não foi encontrada.");
    }


    @Test
    void deveRetornarErroQuandoSenhaContiverCaracteresInvalidos() {
        CartaoRequest request = new CartaoRequest("1234567890123456", "abcd");

        Set<ConstraintViolation<CartaoRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("A senha deve conter apenas dígitos."));
    }

    @Test
    void devePassarQuandoNumeroCartaoESenhaForemValidos() {
        CartaoRequest request = new CartaoRequest("1234567890123456", "1234");

        Set<ConstraintViolation<CartaoRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }
}
