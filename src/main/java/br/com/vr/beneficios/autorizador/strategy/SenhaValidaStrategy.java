package br.com.vr.beneficios.autorizador.strategy;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import org.springframework.stereotype.Component;

@Component
public class SenhaValidaStrategy implements RegraAutorizacao {

    @Override
    public String validar(Cartao cartao, TransacaoRequest request) {
        return (!cartao.getSenha().equals(request.getSenhaCartao())) ? "SENHA_INVALIDA" : "OK";
    }
}
