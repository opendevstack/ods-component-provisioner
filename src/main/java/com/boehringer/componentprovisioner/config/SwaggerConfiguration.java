package com.boehringer.componentprovisioner.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean(name = "apiInfo")
    OpenAPI apiInfo() {
        final String securitySchemeName = "bearerAuth";

        // Copied from: openapi-componentprovisioner-vx.x.x.yaml
        var edpCoreContact = new Contact()
                .name("EDPCore Team")
                .url("https://confluence.biscrum.com/pages/viewpage.action?spaceKey=EDP&title=Welcome");

        var info = new Info()
                .title("Component Provisioner REST API")
                .description("""
                        The Component Provisioner API allows clients to trigger Ansible Automation Platform (AWX) workflows.
                            
                        **NOTES**:
                        - The OpenAPI specification file is also used to [generate](https://openapi-generator.tech/) REST client(s) and a server REST API.
                        - Clients and servers generated from the same OpenAPI specification version are guaranteed to be **compatible**.
                        """)
                .contact(edpCoreContact)
                .version("1.0.0");

        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        Components securityComponents = new Components()
                .addSecuritySchemes(securitySchemeName,securityScheme);

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(securityComponents)
                .info(info);
    }
}