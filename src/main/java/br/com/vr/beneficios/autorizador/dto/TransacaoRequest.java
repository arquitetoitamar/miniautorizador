package br.com.vr.beneficios.autorizador.dto;

import java.math.BigDecimal;

public class TransacaoRequest {

    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;

    // Getters e Setters

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getSenhaCartao() {
        return senhaCartao;
    }

    public void setSenhaCartao(String senhaCartao) {
        this.senhaCartao = senhaCartao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
