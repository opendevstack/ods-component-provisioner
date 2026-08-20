package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.model.CatalogItemMother;
import org.opendevstack.component_provisioner.server.model.CatalogItemUserActionParameterMother;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class MandatoryFieldsValidatorTest {

    @InjectMocks
    private MandatoryFieldsValidator validator;

    @Test
    void givenCatalogItemWithoutUserActions_whenValidate_thenExceptionIsThrown() {
        // given
        CatalogItem catalogItem = CatalogItemMother.of();
        catalogItem.setUserActions(null);

        ProvisionAction action =
                ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> validator.validate(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void givenCatalogItemWithoutProvisionAction_whenValidate_thenExceptionIsThrown() {
        // given
        CatalogItem catalogItem = CatalogItemMother.of();
        catalogItem.getUserActions().getFirst().setId("DELETE");

        ProvisionAction action =
                ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> validator.validate(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("doesn't have a PROVISION user action");
    }

    @Test
    void givenMandatoryParameterWithEmptyList_whenValidate_thenExceptionIsThrown() {
        // given
        CatalogItem catalogItem = CatalogItemMother.of();
        CatalogItemUserActionParameter mandatoryParam = CatalogItemUserActionParameterMother.of(
                "mandatoryParam",
                "defaultValue"
        );
        catalogItem.getUserActions().getFirst().setParameters(List.of(mandatoryParam));

        ProvisionActionParameter actionParam = ProvisionActionParameterMother.of("mandatoryParam", Collections.emptyList());
        ProvisionAction action = ProvisionActionMother.of(List.of(actionParam));

        // when / then
        assertThatThrownBy(() -> validator.validate(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("mandatory and no value was provided");
    }

    @Test
    void givenAValidProvisionAction_whenValidate_thenMandatoryFieldsAreProcessed() {
        // given
        CatalogItem catalogItem = CatalogItemMother.of();
        CatalogItemUserActionParameter mandatoryParam = CatalogItemUserActionParameterMother.of(
                "mandatoryParam",
                "defaultValue"
        );
        catalogItem.getUserActions().getFirst().setParameters(List.of(mandatoryParam));

        ProvisionActionParameter actionParam = ProvisionActionParameterMother.of("mandatoryParam", List.of("defaultValue"));
        ProvisionAction action = ProvisionActionMother.of(List.of(actionParam));

        // when
        validator.validate(action, catalogItem);

        // then
        assertThat(actionParam.getValue()).isEqualTo(List.of("defaultValue"));
    }

    @Test
    void givenMandatoryParameterWithNullValue_whenValidate_thenExceptionIsThrown() {
        // given
        CatalogItem catalogItem = CatalogItemMother.of();
        CatalogItemUserActionParameter mandatoryParam = CatalogItemUserActionParameterMother.of(
                "mandatoryParam",
                "defaultValue"
        );
        catalogItem.getUserActions().getFirst().setParameters(List.of(mandatoryParam));

        ProvisionActionParameter actionParam = ProvisionActionParameterMother.of("mandatoryParam", (Object) null);
        ProvisionAction action = ProvisionActionMother.of(List.of(actionParam));

        // when / then
        assertThatThrownBy(() -> validator.validate(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("mandatory and no value was provided");
    }

    @Test
    void givenMandatoryParameterWithBlankStringValue_whenValidate_thenExceptionIsThrown() {
        // given
        CatalogItem catalogItem = CatalogItemMother.of();
        CatalogItemUserActionParameter mandatoryParam = CatalogItemUserActionParameterMother.of(
                "mandatoryParam",
                "defaultValue"
        );
        catalogItem.getUserActions().getFirst().setParameters(List.of(mandatoryParam));

        ProvisionActionParameter actionParam = ProvisionActionParameterMother.of("mandatoryParam", "   ");
        ProvisionAction action = ProvisionActionMother.of(List.of(actionParam));

        // when / then
        assertThatThrownBy(() -> validator.validate(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("mandatory and no value was provided");
    }

    @Test
    void givenMandatoryParameterWithNonBlankStringValue_whenValidate_thenValidationPasses() {
        // given
        CatalogItem catalogItem = CatalogItemMother.of();
        CatalogItemUserActionParameter mandatoryParam = CatalogItemUserActionParameterMother.of(
                "mandatoryParam",
                "defaultValue"
        );
        catalogItem.getUserActions().getFirst().setParameters(List.of(mandatoryParam));

        ProvisionActionParameter actionParam = ProvisionActionParameterMother.of("mandatoryParam", "someValue");
        ProvisionAction action = ProvisionActionMother.of(List.of(actionParam));

        // when / then (no exception)
        validator.validate(action, catalogItem);
    }
}

