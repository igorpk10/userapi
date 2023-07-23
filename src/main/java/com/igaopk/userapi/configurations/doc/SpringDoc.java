package com.igaopk.userapi.configurations.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDoc {

    @Bean
    public OpenAPI injectOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes(
                                "bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                ).info(
                        new Info()
                                .title("User services")
                                .description("Developed by Igor G. Costa")
                                .contact(new Contact()
                                        .email("igorgonsalves96@hotmail.com")
                                )
                );
    }

}
