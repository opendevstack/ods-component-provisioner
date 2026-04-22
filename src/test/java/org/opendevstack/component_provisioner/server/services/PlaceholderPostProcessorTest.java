package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.server.facade.ProvisionActionWrapper;
import org.opendevstack.component_provisioner.server.facade.ProvisionActionWrapperMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlaceholderPostProcessorTest {

    private final PlaceholderPostProcessor processor = new PlaceholderPostProcessor();

    @Test
    void givenNullProvisionAction_whenProcess_thenReturnsNull() {
        //given
        ProvisionActionWrapper provisionAction = null;

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result).isNull();
    }

    @Test
    void givenProvisionActionWithNullParameters_whenProcess_thenReturnsOriginalAction() {
        //given
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of("action-id", null);

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenProvisionActionWithEmptyParameters_whenProcess_thenReturnsOriginalAction() {
        //given
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of());

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenStringParameterWithPlaceholder_whenProcess_thenReplacesPlaceholder() {
        //given
        ProvisionActionParameter param = ProvisionActionParameterMother.of("key", "value");
        ProvisionActionParameter paramWithPlaceholder = ProvisionActionParameterMother.of("message", "Hello ${key}!");
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of(param, paramWithPlaceholder));

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result.getParametersMap()).hasSize(2);
        assertThat(result.getParametersMap().get("key").getValue()).isEqualTo("value");
        assertThat(result.getParametersMap().get("message").getValue()).isEqualTo("Hello value!");
    }

    @Test
    void givenStringParameterWithMultiplePlaceholders_whenProcess_thenReplacesAllPlaceholders() {
        //given
        ProvisionActionParameter firstName = ProvisionActionParameterMother.of("firstName", "John");
        ProvisionActionParameter lastName = ProvisionActionParameterMother.of("lastName", "Doe");
        ProvisionActionParameter greeting = ProvisionActionParameterMother.of("greeting", "Hello ${firstName} ${lastName}!");
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of(firstName, lastName, greeting));

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        var parameterValues = new ArrayList<>(result.getParametersMap().values());

        assertThat(parameterValues).hasSize(3);
        assertThat(parameterValues.get(2).getValue()).isEqualTo("Hello John Doe!");
    }

    @Test
    void givenStringParameterWithoutPlaceholder_whenProcess_thenReturnsOriginalAction() {
        //given
        ProvisionActionParameter param = ProvisionActionParameterMother.of("message", "Hello World!");
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of(param));

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenNonStringParameter_whenProcess_thenReturnsOriginalParameter() {
        //given
        ProvisionActionParameter intParam = ProvisionActionParameterMother.of("number", 42);
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of(intParam));

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenListParameterWithStringElementsAndPlaceholders_whenProcess_thenReplacesPlaceholdersInListElements() {
        //given
        ProvisionActionParameter key = ProvisionActionParameterMother.of("key", "value");
        ProvisionActionParameter listParam = ProvisionActionParameterMother.of("items", List.of("First ${key}", "Second ${key}"));
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of(key, listParam));

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result.getParametersMap()).hasSize(2);
        @SuppressWarnings("unchecked")
        List<String> replacedList = (List<String>) result.getParametersMap().get("items").getValue();
        assertThat(replacedList).containsExactly("First value", "Second value");
    }

    @Test
    void givenListParameterWithMixedTypes_whenProcess_thenReplacesOnlyStringElements() {
        //given
        ProvisionActionParameter key = ProvisionActionParameterMother.of("key", "value");
        ProvisionActionParameter mixedParam = ProvisionActionParameterMother.of("mixed", List.of("First ${key}", 42, "Second ${key}"));
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of(key, mixedParam));

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result.getParametersMap()).hasSize(2);
        @SuppressWarnings("unchecked")
        List<Object> replacedList = (List<Object>) result.getParametersMap().get("mixed").getValue();
        assertThat(replacedList).containsExactly("First value", 42, "Second value");
    }

    @Test
    void givenListParameterWithoutPlaceholders_whenProcess_thenReturnsOriginalAction() {
        //given
        ProvisionActionParameter listParam = ProvisionActionParameterMother.of("items", List.of("First", "Second"));
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of(listParam));

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenEmptyListParameter_whenProcess_thenReturnsOriginalAction() {
        //given
        ProvisionActionParameter listParam = ProvisionActionParameterMother.of("items", List.of());
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of(listParam));

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenPlaceholderNotDefined_whenProcess_thenLeavesPlaceholderUnchanged() {
        //given
        ProvisionActionParameter param = ProvisionActionParameterMother.of("greeting", "Hello ${undefined}!");
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of(param));

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        var parameterValues = new ArrayList<>(result.getParametersMap().values());

        assertThat(parameterValues).hasSize(1);
        assertThat(parameterValues.getFirst().getValue()).isEqualTo("Hello ${undefined}!");
    }

    @Test
    void givenMultipleParametersWithMixedPlaceholders_whenProcess_thenReplacesAndPreservesImmutability() {
        //given
        ProvisionActionParameter key1 = ProvisionActionParameterMother.of("key1", "value1");
        ProvisionActionParameter key2 = ProvisionActionParameterMother.of("key2", "value2");
        ProvisionActionParameter template = ProvisionActionParameterMother.of("template", "${key1} and ${key2}");
        ProvisionActionWrapper provisionAction = ProvisionActionWrapperMother.of(List.of(key1, key2, template));

        //when
        ProvisionActionWrapper result = processor.process(provisionAction);

        //then
        assertThat(result.getParametersMap()).hasSize(3);
        assertThat(result.getParametersMap().get("key1").getValue()).isEqualTo("value1");
        assertThat(result.getParametersMap().get("key2").getValue()).isEqualTo("value2");
        assertThat(result.getParametersMap().get("template").getValue()).isEqualTo("value1 and value2");
        // Verify immutability: original action should be unchanged
        assertThat(provisionAction.getParametersMap().get("template").getValue()).isEqualTo("${key1} and ${key2}");
    }
}

