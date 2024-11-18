package br.com.vr.beneficios.autorizador.mapper;

import br.com.vr.beneficios.autorizador.dto.CartaoRequest;
import br.com.vr.beneficios.autorizador.dto.CartaoResponse;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartaoMapper {

    CartaoMapper INSTANCE = Mappers.getMapper(CartaoMapper.class);

    /**
     * Mapeia o objeto CartaoRequest para a entidade Cartao.
     *
     * @param request Objeto CartaoRequest com os dados da requisição.
     * @return Entidade Cartao com os dados mapeados.
     */
    @Mapping(target = "numeroCartao", source = "numeroCartao")
    @Mapping(target = "senha", source = "senha")
    @Mapping(target = "saldo", ignore = true) // Ignorando o saldo, pois ele é inicializado com um valor padrão
    Cartao toEntity(CartaoRequest request);

    /**
     * Mapeia a entidade Cartao para CartaoResponse.
     *
     * @param cartao Entidade Cartao.
     * @return Objeto CartaoResponse.
     */
    @Mapping(target = "numeroCartao", source = "numeroCartao")
    @Mapping(target = "saldo", source = "saldo")
    CartaoResponse toResponse(Cartao cartao);
}
