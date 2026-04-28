package org.opendevstack.component_provisioner.server.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentParameter;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequestParametersInner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProvisioningStatusUpdateRequestParametersInnerMapperTest {

    private final ProvisioningStatusUpdateRequestParametersInnerMapper mapper = Mappers.getMapper(ProvisioningStatusUpdateRequestParametersInnerMapper.class);

    @Test
    void givenProjectComponentParameter_whenToTargetIsCalled_thenReturnsMappedObject() {
        // given
        ProjectComponentParameter source = new ProjectComponentParameter();
        source.setName("param1");
        source.setValues(List.of("value1", "value2"));

        // when
        ProvisioningStatusUpdateRequestParametersInner result = mapper.toTarget(source);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("param1");
        assertThat(result.getValues()).containsExactly("value1", "value2");
    }

    @Test
    void givenNullSource_whenToTargetIsCalled_thenReturnsNull() {
        // given
        ProjectComponentParameter source = null;

        // when
        ProvisioningStatusUpdateRequestParametersInner result = mapper.toTarget(source);

        // then
        assertThat(result).isNull();
    }
}
