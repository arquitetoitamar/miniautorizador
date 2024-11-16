package br.com.vr.beneficios.autorizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.vr.beneficios.autorizador.repository.cartao")
public class AutorizadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutorizadorApplication.class, args);
	}

}
