package br.com.vr.beneficios.autorizador.repository.cartao;

import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartaoRepository extends CrudRepository<Cartao, Long> {

    /**
     * Busca um cartão pelo número do cartão.
     *
     * @param numeroCartao Número do cartão.
     * @return Um Optional contendo o cartão, se encontrado.
     */
    Optional<Cartao> findByNumeroCartao(String numeroCartao);
}