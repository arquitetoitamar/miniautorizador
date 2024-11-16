package br.com.vr.beneficios.autorizador.strategy;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import org.springframework.stereotype.Component;

@Component
public class CartaoExistenteStrategy implements RegraAutorizacao {

    @Override
    public String validar(Cartao cartao, TransacaoRequest request) {
        return (cartao == null) ? "CARTAO_INEXISTENTE" : "OK";
    }
}
