package org.opendevstack.component_provisioner.server.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentParameter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CreateIncidentParameterMapperTest {

    private final CreateIncidentParameterMapper mapper = Mappers.getMapper(CreateIncidentParameterMapper.class);

    @Test
    void givenAProjectComponentParameterWithValues_whenToTargetIsCalled_thenReturnsMappedCreateIncidentParameter() {
        // given
        var source = ProjectComponentParameter.builder()
                .name("param1")
                .values(List.of("value1", "value2"))
                .build();

        // when
        var result = mapper.toTarget(source);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("param1");
        assertThat(result.getValue()).isEqualTo("value1");
        assertThat(result.getType()).isEqualTo("string");
    }

    @Test
    void givenAProjectComponentParameterWithNullValues_whenToTargetIsCalled_thenReturnsCreateIncidentParameterWithNullValue() {
        // given
        var source = ProjectComponentParameter.builder()
                .name("param2")
                .values(null)
                .build();

        // when
        var result = mapper.toTarget(source);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("param2");
        assertThat(result.getValue()).isNull();
    }

    @Test
    void givenAProjectComponentParameterWithEmptyValues_whenToTargetIsCalled_thenReturnsCreateIncidentParameterWithNullValue() {
        // given
        var source = ProjectComponentParameter.builder()
                .name("param3")
                .values(List.of())
                .build();

        // when
        var result = mapper.toTarget(source);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("param3");
        assertThat(result.getValue()).isNull();
    }
}
