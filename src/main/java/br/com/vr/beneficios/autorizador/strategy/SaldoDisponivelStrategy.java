package br.com.vr.beneficios.autorizador.strategy;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SaldoDisponivelStrategy implements RegraAutorizacao {

    @Override
    public String validar(Cartao cartao, TransacaoRequest request) {
        BigDecimal saldoAtual = cartao.getSaldo();
        BigDecimal valorTransacao = request.getValor();

        return (saldoAtual.compareTo(valorTransacao) < 0) ? "SALDO_INSUFICIENTE" : "OK";
    }
}
