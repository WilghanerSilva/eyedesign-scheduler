package com.eyedesign.scheduler.infra.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    String schemaName = "bearerAuth";
    String bearerFormat = "JWT";
    String scheme = "bearer";

    @Bean
    public OpenAPI caseOpenAPI(){
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(schemaName)).components(new Components()
                        .addSecuritySchemes(schemaName, new SecurityScheme()
                                .name(schemaName)
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat(bearerFormat)
                                .in(SecurityScheme.In.HEADER)
                                .scheme(scheme)
                        )
                )
                .info(new Info()
                        .title("Eye Design Scheduler")
                        .description("Agendamento para desgin de sombrancelhas")
                        .version("1.0")
                );
    }

}
