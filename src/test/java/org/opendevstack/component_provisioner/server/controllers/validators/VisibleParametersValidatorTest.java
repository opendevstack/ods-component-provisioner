package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserAction;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisibleParametersValidatorTest {

    private static final String CATALOG_ITEM_TITLE = "My Catalog Item";

    private final VisibleParametersValidator visibleParametersValidator = new VisibleParametersValidator();

    @Test
    void givenNullCatalogItem_whenValidatingReceivesOnlyVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var action = ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> visibleParametersValidator.validate(action, null))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void givenCatalogItemWithoutUserActions_whenValidatingVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var catalogItem = CatalogItem.builder().title(CATALOG_ITEM_TITLE).userActions(null).build();
        var action = ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> visibleParametersValidator.validate(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void givenCatalogItemWithoutProvisionUserAction_whenValidatingVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var deleteUserAction = CatalogItemUserAction.builder().id("DELETE").parameters(List.of()).build();
        var catalogItem = CatalogItem.builder().title(CATALOG_ITEM_TITLE).userActions(List.of(deleteUserAction)).build();
        var action = ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> visibleParametersValidator.validate(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("doesn't have a PROVISION user action");
    }

    @Test
    void givenParameterNotDefinedInCatalog_whenValidatingVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var catalogItem = catalogItemWithProvisionParameters(List.of(catalogParameter("known_param", true, true)));
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("unknown_param", "value")
        ));

        // when / then
        assertThatThrownBy(() -> visibleParametersValidator.validate(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("unknown_param")
                .hasMessageContaining(CATALOG_ITEM_TITLE);
    }

    @Test
    void givenNotVisibleParameter_whenValidatingReceivesOnlyVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var catalogItem = catalogItemWithProvisionParameters(List.of(catalogParameter("hidden_param", false, true)));
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("hidden_param", "value")
        ));

        // when / then
        assertThatThrownBy(() -> visibleParametersValidator.validate(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("hidden_param")
                .hasMessageContaining(CATALOG_ITEM_TITLE);
    }

    @Test
    void givenParameterWithNullVisibility_whenValidatingVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var catalogItem = catalogItemWithProvisionParameters(List.of(catalogParameter("null_visibility_param", null, true)));
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("null_visibility_param", "value")
        ));

        // when / then
        assertThatThrownBy(() -> visibleParametersValidator.validate(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("null_visibility_param")
                .hasMessageContaining(CATALOG_ITEM_TITLE);
    }

    @Test
    void givenAllVisibleParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        // given
        var catalogItem = catalogItemWithProvisionParameters(List.of(
                catalogParameter("mandatory_param", true, true),
                catalogParameter("optional_param", true, false)
        ));
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("mandatory_param", "val1"),
                ProvisionActionParameterMother.of("optional_param", "val2")
        ));

        // when / then
        assertThatNoException().isThrownBy(
                () -> visibleParametersValidator.validate(action, catalogItem));
    }

    @Test
    void givenProvisionUserActionWithoutParameters_whenValidatingOnlyInternalParameters_thenDoesNotThrow() {
        // given
        var userAction = CatalogItemUserAction.builder().id("PROVISION").parameters(null).build();
        var catalogItem = CatalogItem.builder().title(CATALOG_ITEM_TITLE).userActions(List.of(userAction)).build();
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("catalog_item_id", "cat-123"),
                ProvisionActionParameterMother.of("project_key", "pkey")
        ));

        // when / then
        assertThatNoException().isThrownBy(
                () -> visibleParametersValidator.validate(action, catalogItem));
    }

    @Test
    void givenInternalAndVisibleParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        // given
        var catalogItem = catalogItemWithProvisionParameters(List.of(catalogParameter("visible_param", true, true)));
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("catalog_item_id", "cat-123"),
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("visible_param", "value")
        ));

        // when / then
        assertThatNoException().isThrownBy(
                () -> visibleParametersValidator.validate(action, catalogItem));
    }

    @Test
    void givenNoParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        // given
        var catalogItem = catalogItemWithProvisionParameters(List.of(catalogParameter("some_param", true, true)));
        var action = ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatNoException().isThrownBy(
                () -> visibleParametersValidator.validate(action, catalogItem));
    }

    private static CatalogItem catalogItemWithProvisionParameters(List<CatalogItemUserActionParameter> parameters) {
        var provisionUserAction = CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(parameters)
                .build();

        return CatalogItem.builder()
                .title(CATALOG_ITEM_TITLE)
                .userActions(List.of(provisionUserAction))
                .build();
    }

    private static CatalogItemUserActionParameter catalogParameter(String name, Boolean visible, boolean required) {
        return CatalogItemUserActionParameter.builder()
                .name(name)
                .visible(visible)
                .required(required)
                .build();
    }
}

