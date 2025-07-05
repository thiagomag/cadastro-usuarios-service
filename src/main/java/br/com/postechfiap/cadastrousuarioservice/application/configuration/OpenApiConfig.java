package br.com.postechfiap.cadastrousuarioservice.application.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth"; // Nome do esquema de segurança

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName)) // Adiciona o requisito de segurança para todas as operações
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP) // Tipo de segurança HTTP
                                        .scheme("bearer") // Esquema "bearer" para tokens JWT
                                        .bearerFormat("JWT"))) // Formato do token
                .info(new Info()
                        .title("Autenticacao Service API")
                        .description("API para gestão de usuários e autenticação no sistema.")
                        .version("1.0.0"));
    }
}
