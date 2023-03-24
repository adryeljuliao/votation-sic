package com.votation.api.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI storeConclusionBffOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("votation-api")
                        .description("Responsável por cadastrar pautas e realizar votações")
                        .version("v0.0.1-SNAPSHOT"));
    }
}