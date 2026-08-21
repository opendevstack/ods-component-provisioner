package org.opendevstack.component_provisioner.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiConfigurationTest {

    @Test
    void givenOpenApiConfiguration_whenApiInfoIsBuilt_thenContainsConfiguredSecuritySchemesAndMetadata() {
        // given
        var openApiConfiguration = new OpenApiConfiguration();

        // when
        var openApi = openApiConfiguration.apiInfo();

        // then
        assertThat(openApi.getInfo()).isNotNull();
        assertThat(openApi.getInfo().getTitle()).isEqualTo("Component Provisioner REST API");
        assertThat(openApi.getInfo().getVersion()).isEqualTo("1.0.0");

        assertThat(openApi.getComponents()).isNotNull();
        assertThat(openApi.getComponents().getSecuritySchemes()).containsKeys("bearerAuth", "basicAuth");

        assertThat(openApi.getComponents().getSecuritySchemes().get("bearerAuth").getType())
                .isEqualTo(SecurityScheme.Type.HTTP);
        assertThat(openApi.getComponents().getSecuritySchemes().get("bearerAuth").getScheme())
                .isEqualTo("bearer");
        assertThat(openApi.getComponents().getSecuritySchemes().get("basicAuth").getScheme())
                .isEqualTo("basic");

        assertThat(openApi.getSecurity()).isNotEmpty();
        assertThat(openApi.getSecurity().getFirst()).containsKey("bearerAuth");
    }
}

