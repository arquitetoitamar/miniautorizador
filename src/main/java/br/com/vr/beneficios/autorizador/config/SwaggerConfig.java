package br.com.vr.beneficios.autorizador.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mini Autorizador API")
                        .version("1.0")
                        .description("API para gerenciamento de cartões e transações")
                        .contact(new Contact()
                                .name("Seu Nome")
                                .email("seu.email@exemplo.com")
                                .url("https://www.seusite.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor Local")
                ));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(false);
    }

    @Bean
    public GroupedOpenApi apiControllers() {
        return GroupedOpenApi.builder()
                .group("Autorizador")
                .packagesToScan("br.com.vr.beneficios.autorizador.controller")
                .build();
    }
}
