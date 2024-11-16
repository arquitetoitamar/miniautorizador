package br.com.vr.beneficios.autorizador.model.transacao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "transacoes")
public class Transacao {

    @Id
    private String idTransacao;
    private String numeroCartao;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String status;

    public Transacao() {
        // Construtor vazio para deserialização
    }

    public Transacao(String numeroCartao, BigDecimal valor, String status) {
        this.numeroCartao = numeroCartao;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
        this.status = status;
    }

    // Getters e Setters

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Métodos auxiliares

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(idTransacao, transacao.idTransacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacao);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "idTransacao='" + idTransacao + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", valor=" + valor +
                ", dataHora=" + dataHora +
                ", status='" + status + '\'' +
                '}';
    }
}
