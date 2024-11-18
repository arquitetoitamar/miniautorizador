package br.com.vr.beneficios.autorizador.model.transacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Data
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
}
