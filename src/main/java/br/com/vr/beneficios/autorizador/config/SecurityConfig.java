package br.com.vr.beneficios.autorizador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Para atende as regras de autenticacao Basic
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF para facilitar os testes
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api-docs/**", "/swagger-ui/**").permitAll() // Permite acesso público ao Swagger
                        .anyRequest().authenticated() // Requer autenticação para todas as outras requisições
                )
                .httpBasic(); // Habilita a autenticação HTTP Basic

        return http.build();
    }
}
