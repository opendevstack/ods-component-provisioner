package org.opendevstack.component_provisioner;

import com.fasterxml.jackson.databind.Module;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullableModule;

import static org.assertj.core.api.Assertions.assertThat;

class ComponentProvisionerApplicationTest {

    @Test
    void givenApplication_whenJsonNullableModuleIsCalled_thenReturnsJsonNullableModule() {
        // given
        ComponentProvisionerApplication application = new ComponentProvisionerApplication();

        // when
        Module module = application.jsonNullableModule();

        // then
        assertThat(module).isInstanceOf(JsonNullableModule.class);
    }
}
