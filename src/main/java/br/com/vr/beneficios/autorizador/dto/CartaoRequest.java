package br.com.vr.beneficios.autorizador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CartaoRequest implements Serializable {

    @NotBlank(message = "O número do cartão não pode estar em branco.")
    @Size(min = 16, max = 16, message = "O número do cartão deve ter 16 dígitos.")
    @Pattern(regexp = "\\d{16}", message = "O número do cartão deve conter apenas dígitos.")
    private String numeroCartao;

    @NotBlank(message = "A senha não pode estar em branco.")
    @Size(min = 4, max = 4, message = "A senha deve ter 4 dígitos.")
    @Pattern(regexp = "\\d{4}", message = "A senha deve conter apenas dígitos.")
    private String senha;

}