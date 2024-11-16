package br.com.vr.beneficios.autorizador.strategy;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;

public interface RegraAutorizacao {
    String validar(Cartao cartao, TransacaoRequest request);
}
