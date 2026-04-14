package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlaceholderPostProcessorTest {

    private final PlaceholderPostProcessor processor = new PlaceholderPostProcessor();

    @Test
    void givenNullProvisionAction_whenProcess_thenReturnsNull() {
        //given
        ProvisionAction provisionAction = null;

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result).isNull();
    }

    @Test
    void givenProvisionActionWithNullParameters_whenProcess_thenReturnsOriginalAction() {
        //given
        ProvisionAction provisionAction = ProvisionAction.builder()
                .id("action-id")
                .parameters(null)
                .build();

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenProvisionActionWithEmptyParameters_whenProcess_thenReturnsOriginalAction() {
        //given
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of());

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenStringParameterWithPlaceholder_whenProcess_thenReplacesPlaceholder() {
        //given
        ProvisionActionParameter param = ProvisionActionParameterMother.of("key", "value");
        ProvisionActionParameter paramWithPlaceholder = ProvisionActionParameterMother.of("message", "Hello ${key}!");
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of(param, paramWithPlaceholder));

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result.getParameters()).hasSize(2);
        assertThat(result.getParameters().get(0).getValue()).isEqualTo("value");
        assertThat(result.getParameters().get(1).getValue()).isEqualTo("Hello value!");
    }

    @Test
    void givenStringParameterWithMultiplePlaceholders_whenProcess_thenReplacesAllPlaceholders() {
        //given
        ProvisionActionParameter firstName = ProvisionActionParameterMother.of("firstName", "John");
        ProvisionActionParameter lastName = ProvisionActionParameterMother.of("lastName", "Doe");
        ProvisionActionParameter greeting = ProvisionActionParameterMother.of("greeting", "Hello ${firstName} ${lastName}!");
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of(firstName, lastName, greeting));

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result.getParameters()).hasSize(3);
        assertThat(result.getParameters().get(2).getValue()).isEqualTo("Hello John Doe!");
    }

    @Test
    void givenStringParameterWithoutPlaceholder_whenProcess_thenReturnsOriginalAction() {
        //given
        ProvisionActionParameter param = ProvisionActionParameterMother.of("message", "Hello World!");
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of(param));

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenNonStringParameter_whenProcess_thenReturnsOriginalParameter() {
        //given
        ProvisionActionParameter intParam = ProvisionActionParameterMother.of("number", 42);
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of(intParam));

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenListParameterWithStringElementsAndPlaceholders_whenProcess_thenReplacesPlaceholdersInListElements() {
        //given
        ProvisionActionParameter key = ProvisionActionParameterMother.of("key", "value");
        ProvisionActionParameter listParam = ProvisionActionParameterMother.of("items", List.of("First ${key}", "Second ${key}"));
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of(key, listParam));

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result.getParameters()).hasSize(2);
        @SuppressWarnings("unchecked")
        List<String> replacedList = (List<String>) result.getParameters().get(1).getValue();
        assertThat(replacedList).containsExactly("First value", "Second value");
    }

    @Test
    void givenListParameterWithMixedTypes_whenProcess_thenReplacesOnlyStringElements() {
        //given
        ProvisionActionParameter key = ProvisionActionParameterMother.of("key", "value");
        ProvisionActionParameter mixedParam = ProvisionActionParameterMother.of("mixed", List.of("First ${key}", 42, "Second ${key}"));
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of(key, mixedParam));

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result.getParameters()).hasSize(2);
        @SuppressWarnings("unchecked")
        List<Object> replacedList = (List<Object>) result.getParameters().get(1).getValue();
        assertThat(replacedList).containsExactly("First value", 42, "Second value");
    }

    @Test
    void givenListParameterWithoutPlaceholders_whenProcess_thenReturnsOriginalAction() {
        //given
        ProvisionActionParameter listParam = ProvisionActionParameterMother.of("items", List.of("First", "Second"));
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of(listParam));

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenEmptyListParameter_whenProcess_thenReturnsOriginalAction() {
        //given
        ProvisionActionParameter listParam = ProvisionActionParameterMother.of("items", List.of());
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of(listParam));

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result).isEqualTo(provisionAction);
    }

    @Test
    void givenPlaceholderNotDefined_whenProcess_thenLeavesPlaceholderUnchanged() {
        //given
        ProvisionActionParameter param = ProvisionActionParameterMother.of("greeting", "Hello ${undefined}!");
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of(param));

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result.getParameters()).hasSize(1);
        assertThat(result.getParameters().getFirst().getValue()).isEqualTo("Hello ${undefined}!");
    }

    @Test
    void givenMultipleParametersWithMixedPlaceholders_whenProcess_thenReplacesAndPreservesImmutability() {
        //given
        ProvisionActionParameter key1 = ProvisionActionParameterMother.of("key1", "value1");
        ProvisionActionParameter key2 = ProvisionActionParameterMother.of("key2", "value2");
        ProvisionActionParameter template = ProvisionActionParameterMother.of("template", "${key1} and ${key2}");
        ProvisionAction provisionAction = ProvisionActionMother.of(List.of(key1, key2, template));

        //when
        ProvisionAction result = processor.process(provisionAction);

        //then
        assertThat(result.getParameters()).hasSize(3);
        assertThat(result.getParameters().get(0).getValue()).isEqualTo("value1");
        assertThat(result.getParameters().get(1).getValue()).isEqualTo("value2");
        assertThat(result.getParameters().get(2).getValue()).isEqualTo("value1 and value2");
        // Verify immutability: original action should be unchanged
        assertThat(provisionAction.getParameters().get(2).getValue()).isEqualTo("${key1} and ${key2}");
    }
}

