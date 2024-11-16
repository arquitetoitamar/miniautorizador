package br.com.vr.beneficios.autorizador.mapper;

import br.com.vr.beneficios.autorizador.dto.TransacaoResponse;
import br.com.vr.beneficios.autorizador.model.transacao.Transacao;

public class TransacaoMapper {

    public static TransacaoResponse toResponse(Transacao transacao) {
        return new TransacaoResponse(transacao.getStatus());
    }
}
