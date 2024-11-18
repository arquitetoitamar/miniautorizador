package br.com.vr.beneficios.autorizador.mapper;

import br.com.vr.beneficios.autorizador.dto.TransacaoResponse;
import br.com.vr.beneficios.autorizador.model.transacao.Transacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    TransacaoMapper INSTANCE = Mappers.getMapper(TransacaoMapper.class);

    /**
     * Mapeia a entidade Transacao para TransacaoResponse.
     *
     * @param transacao Entidade Transacao.
     * @return Objeto TransacaoResponse.
     */
    @Mapping(target = "status", source = "status")
    TransacaoResponse toResponse(Transacao transacao);
}
