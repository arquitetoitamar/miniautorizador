package br.com.vr.beneficios.autorizador.mapper;

import br.com.vr.beneficios.autorizador.dto.CartaoRequest;
import br.com.vr.beneficios.autorizador.dto.CartaoResponse;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;

public class CartaoMapper {
    /**
     * Mapeia o objeto CartaoRequest para a entidade Cartao.
     *
     * @param request Objeto CartaoRequest com os dados da requisição.
     * @return Entidade Cartao com os dados mapeados.
     */
    public static Cartao toEntity(CartaoRequest request) {
        return new Cartao(request.getNumeroCartao(), request.getSenha());
    }

    /**
     * Mapeia a entidade Cartao para CartaoResponse.
     *
     * @param cartao Entidade Cartao.
     * @return Objeto CartaoResponse.
     */
    public static CartaoResponse toResponse(Cartao cartao) {
        return new CartaoResponse(cartao.getNumeroCartao(), cartao.getSaldo());
    }
}
