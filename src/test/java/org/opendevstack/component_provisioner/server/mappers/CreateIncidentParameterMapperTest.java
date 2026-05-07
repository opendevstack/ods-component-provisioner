package org.opendevstack.component_provisioner.server.mappers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentParameter;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CreateIncidentParameterMapperTest {

    @Spy
    private final CreateIncidentParameterMapper mapper = Mappers.getMapper(CreateIncidentParameterMapper.class);

    @Test
    void givenAProjectComponentParameterWithValues_whenToTargetIsCalled_thenReturnsMappedCreateIncidentParameter() {
        // given
        var param = CatalogItemUserActionParameter.builder()
                .name("param1")
                .type(ParameterType.STRING.getValue())
                .build();
        var source = ProjectComponentParameter.builder()
                .name("param1")
                .values(List.of("value1", "value2"))
                .build();

        // when
        var result = mapper.toTarget(param, source.getValues());

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("param1");
        assertThat(result.getValue()).isEqualTo("value1");
        assertThat(result.getType()).isEqualTo("string");
    }

    @Test
    void givenAProjectComponentParameterWithNullValues_whenToTargetIsCalled_thenReturnsCreateIncidentParameterWithNullValue() {
        // given
        var param = CatalogItemUserActionParameter.builder()
                .name("param2")
                .type(ParameterType.STRING.getValue())
                .build();
        var source = ProjectComponentParameter.builder()
                .name("param2")
                .values(null)
                .build();

        // when
        var result = mapper.toTarget(param, source.getValues());

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("param2");
        assertThat(result.getValue()).isNull();
    }

    @Test
    void givenAProjectComponentParameterWithEmptyValues_whenToTargetIsCalled_thenReturnsCreateIncidentParameterWithNullValue() {
        // given
        var param = CatalogItemUserActionParameter.builder()
                .name("param3")
                .type(ParameterType.STRING.getValue())
                .build();
        var source = ProjectComponentParameter.builder()
                .name("param3")
                .values(List.of())
                .build();

        // when
        var result = mapper.toTarget(param, source.getValues());

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("param3");
        assertThat(result.getValue()).isNull();
    }

    @Test
    void givenNullValues_whenResolveValueIsCalled_thenReturnsNull() {
        // given
        var source = ProjectComponentParameter.builder().values(null).build();

        // when
        Object result = mapper.resolveValue(ParameterType.STRING.getValue(), source.getValues());

        // then
        assertThat(result).isNull();
    }

    @Test
    void givenEmptyValues_whenResolveValueIsCalled_thenReturnsNull() {
        // given
        var source = ProjectComponentParameter.builder().values(List.of()).build();

        // when
        Object result = mapper.resolveValue(ParameterType.STRING.getValue(), source.getValues());

        // then
        assertThat(result).isNull();
    }

    @Test
    void givenValues_whenResolveValueIsCalled_thenReturnsFirstElement() {
        // given
        var source = ProjectComponentParameter.builder().values(List.of("first", "second")).build();

        // when
        Object result = mapper.resolveValue(ParameterType.STRING.getValue(), source.getValues());

        // then
        assertThat(result).isEqualTo("first");
    }
}
