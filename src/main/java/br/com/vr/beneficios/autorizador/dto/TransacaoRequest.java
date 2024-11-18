package br.com.vr.beneficios.autorizador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class TransacaoRequest {

    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransacaoRequest that = (TransacaoRequest) o;
        return Objects.equals(numeroCartao, that.numeroCartao) &&
                Objects.equals(senhaCartao, that.senhaCartao) &&
                Objects.equals(valor.doubleValue(), that.valor.doubleValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCartao, senhaCartao, valor);
    }

}
