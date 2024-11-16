package br.com.vr.beneficios.autorizador.dto;

public class TransacaoResponse {

    private String status;

    public TransacaoResponse(String status) {
        this.status = status;
    }

    // Getters e Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
