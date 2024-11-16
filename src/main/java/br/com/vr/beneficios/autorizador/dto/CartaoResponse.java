package br.com.vr.beneficios.autorizador.dto;

import java.math.BigDecimal;

public class CartaoResponse {

    private String numeroCartao;
    private BigDecimal saldo;

    public CartaoResponse(String numeroCartao, BigDecimal saldo) {
        this.numeroCartao = numeroCartao;
        this.saldo = saldo;
    }

    // Getters e Setters

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
