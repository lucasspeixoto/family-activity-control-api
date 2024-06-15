package com.lspeixotodev.family_activity_control_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Family Control Api")
                        .version("v1")
                        .description("Documentation about Family Control Platform API`s")
                        .termsOfService("https://github.com/lucasspeixoto/family-activity-control-api")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/lucasspeixoto/family-activity-control-api")
                        )
                );
    }
}
