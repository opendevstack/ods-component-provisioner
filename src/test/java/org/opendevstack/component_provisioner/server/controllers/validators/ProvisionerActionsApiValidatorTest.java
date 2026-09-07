package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserAction;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectComponentAlreadyProvisionedException;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiValidatorTest {

    @Mock
    private MandatoryFieldsValidator mandatoryFieldsValidator;

    @Mock
    private ComponentsValidator componentsValidator;

    @Mock
    private InputParamsValidator inputParamsValidator;

    @InjectMocks
    private ProvisionerActionsApiValidator provisionerActionsApiValidator;

    @Test
    void givenAlreadyProvisionedComponent_whenValidating_thenThrowsProjectComponentAlreadyProvisionedException() {
        // given
        var projectKey = "pkey";
        var componentId = "cid";
        var accessToken = "accessToken";

        var params = List.of(
                ProvisionActionParameterMother.of("project_key", projectKey),
                ProvisionActionParameterMother.of("component_id", componentId),
                ProvisionActionParameterMother.of("catalog_item_id", "111"),
                ProvisionActionParameterMother.of("access_token", accessToken)
        );

        var action = ProvisionActionMother.of(params);

        doThrow(new ProjectComponentAlreadyProvisionedException("This component name already exists, please choose another name."))
                .when(componentsValidator)
                .validate(action);

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validate(action))
                .isInstanceOf(ProjectComponentAlreadyProvisionedException.class);
    }

    @Test
    void givenInvalidInputParams_whenValidating_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "";
        var accessToken = "accessToken";
        var componentId = "cid";

        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", projectKey),
                ProvisionActionParameterMother.of("component_id", componentId),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", accessToken)
        ));

        doThrow(new InvalidRestEntityException("project_key, access_token, component_id are required."))
                .when(inputParamsValidator)
                .validate(action);

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validate(action))
                .isInstanceOf(InvalidRestEntityException.class);
    }

    @Test
    void givenValidInput_whenValidating_thenDoesNotThrow() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        // when / then
        assertThatNoException().isThrownBy(() -> provisionerActionsApiValidator.validate(action));
    }

    @Test
    void givenMandatoryFieldsValidatorThrows_whenValidatingMandatoryFields_thenThrowsInvalidRestEntityException() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        doThrow(new InvalidRestEntityException("Mandatory field missing"))
                .when(mandatoryFieldsValidator)
                .validate(any(), any());

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateMandatoryFields(action, new CatalogItem()))
                .isInstanceOf(InvalidRestEntityException.class);
    }

    @Test
    void givenValidMandatoryFields_whenValidatingMandatoryFields_thenDoesNotThrow() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        // when / then
        assertThatNoException().isThrownBy(() -> provisionerActionsApiValidator.validateMandatoryFields(action, new CatalogItem()));
    }

    @Test
    void givenComponentsValidatorThrows_whenValidating_thenThrowsRuntimeException() {
        // given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        doThrow(new RuntimeException("Service error"))
                .when(componentsValidator)
                .validate(any());

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validate(action))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void givenNullCatalogItem_whenValidatingReceivesOnlyVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var action = ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, null))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void givenCatalogItemWithoutUserActions_whenValidatingVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var catalogItem = CatalogItem.builder().title("My Item").userActions(null).build();
        var action = ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void givenCatalogItemWithoutProvisionUserAction_whenValidatingVisibleParams_thenThrowsInvalidRestEntityException() {
        // given
        var userAction = CatalogItemUserAction.builder().id("DELETE").parameters(List.of()).build();
        var catalogItem = CatalogItem.builder().title("My Item").userActions(List.of(userAction)).build();
        var action = ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("doesn't have a PROVISION user action");
    }

    @Test
    void givenParameterNotDefinedInCatalog_whenValidatingVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var visibleParam = CatalogItemUserActionParameter.builder()
                .name("known_param")
                .visible(true)
                .build();
        var userAction = CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(List.of(visibleParam))
                .build();
        var catalogItem = CatalogItem.builder()
                .title("My Catalog Item")
                .userActions(List.of(userAction))
                .build();
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("unknown_param", "value")
        ));

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("unknown_param")
                .hasMessageContaining("My Catalog Item");
    }

    @Test
    void givenNotVisibleParameter_whenValidatingReceivesOnlyVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var hiddenParam = CatalogItemUserActionParameter.builder()
                .name("hidden_param")
                .visible(false)
                .build();
        var userAction = CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(List.of(hiddenParam))
                .build();
        var catalogItem = CatalogItem.builder()
                .title("My Catalog Item")
                .userActions(List.of(userAction))
                .build();
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("hidden_param", "value")
        ));

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("hidden_param")
                .hasMessageContaining("My Catalog Item");
    }

    @Test
    void givenParameterWithNullVisibility_whenValidatingVisibleParameters_thenThrowsInvalidRestEntityException() {
        // given
        var paramWithNullVisibility = CatalogItemUserActionParameter.builder()
                .name("null_visibility_param")
                .visible(null)
                .build();
        var userAction = CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(List.of(paramWithNullVisibility))
                .build();
        var catalogItem = CatalogItem.builder()
                .title("My Catalog Item")
                .userActions(List.of(userAction))
                .build();
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("null_visibility_param", "value")
        ));

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("null_visibility_param")
                .hasMessageContaining("My Catalog Item");
    }

    @Test
    void givenAllVisibleParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        // given
        var mandatoryVisible = CatalogItemUserActionParameter.builder()
                .name("mandatory_param")
                .visible(true)
                .required(true)
                .build();
        var optionalVisible = CatalogItemUserActionParameter.builder()
                .name("optional_param")
                .visible(true)
                .required(false)
                .build();
        var userAction = CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(List.of(mandatoryVisible, optionalVisible))
                .build();
        var catalogItem = CatalogItem.builder()
                .title("My Catalog Item")
                .userActions(List.of(userAction))
                .build();
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("mandatory_param", "val1"),
                ProvisionActionParameterMother.of("optional_param", "val2")
        ));

        // when / then
        assertThatNoException().isThrownBy(
                () -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem));
    }

    @Test
    void givenOnlyVisibleOptionalParameter_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        // given
        var optionalVisible = CatalogItemUserActionParameter.builder()
                .name("optional_param")
                .visible(true)
                .required(false)
                .build();
        var userAction = CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(List.of(optionalVisible))
                .build();
        var catalogItem = CatalogItem.builder()
                .title("My Catalog Item")
                .userActions(List.of(userAction))
                .build();
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("optional_param", "value")
        ));

        // when / then
        assertThatNoException().isThrownBy(
                () -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem));
    }

    @Test
    void givenNoParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        // given
        var visibleParam = CatalogItemUserActionParameter.builder()
                .name("some_param")
                .visible(true)
                .build();
        var userAction = CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(List.of(visibleParam))
                .build();
        var catalogItem = CatalogItem.builder()
                .title("My Catalog Item")
                .userActions(List.of(userAction))
                .build();
        var action = ProvisionActionMother.of(Collections.emptyList());

        // when / then
        assertThatNoException().isThrownBy(
                () -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem));
    }

    @Test
    void givenOnlyInternalParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        // given
        // catalog_item_id and project_key are not defined in catalog params but must always be allowed
        var userAction = CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(List.of())
                .build();
        var catalogItem = CatalogItem.builder()
                .title("My Catalog Item")
                .userActions(List.of(userAction))
                .build();
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("catalog_item_id", "cat-123"),
                ProvisionActionParameterMother.of("project_key", "pkey")
        ));

        // when / then
        assertThatNoException().isThrownBy(
                () -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem));
    }

    @Test
    void givenInternalAndVisibleParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        // given
        // catalog_item_id and project_key mixed with regular visible params should still pass
        var visibleParam = CatalogItemUserActionParameter.builder()
                .name("visible_param")
                .visible(true)
                .build();
        var userAction = CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(List.of(visibleParam))
                .build();
        var catalogItem = CatalogItem.builder()
                .title("My Catalog Item")
                .userActions(List.of(userAction))
                .build();
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("catalog_item_id", "cat-123"),
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("visible_param", "value")
        ));

        // when / then
        assertThatNoException().isThrownBy(
                () -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem));
    }
}
