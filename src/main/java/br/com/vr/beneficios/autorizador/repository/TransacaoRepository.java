package br.com.vr.beneficios.autorizador.repository;

import br.com.vr.beneficios.autorizador.model.transacao.Transacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TransacaoRepository extends MongoRepository<Transacao, String> {

    /**
     * Busca um cartão pelo número do cartão.
     *
     * @param numeroCartao Número do cartão.
     * @return Um Optional contendo o cartão, se encontrado.
     */
    Optional<Transacao> findByNumeroCartao(String numeroCartao);
}