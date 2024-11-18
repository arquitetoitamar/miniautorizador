package br.com.vr.beneficios.autorizador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class CartaoResponse implements Serializable {

    private String numeroCartao;
    private BigDecimal saldo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartaoResponse that = (CartaoResponse) o;
        return Objects.equals(numeroCartao, that.numeroCartao) &&
                Objects.equals(saldo.doubleValue(), that.saldo.doubleValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCartao, saldo);
    }
}
