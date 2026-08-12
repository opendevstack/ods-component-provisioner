package org.opendevstack.component_provisioner.server.controllers.validators;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserAction;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectComponentAlreadyProvisionedException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.UserNotAllowedException;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.ProjectsInfoService;
import org.opendevstack.component_provisioner.server.services.restrictions.evaluators.GroupsRestrictionsEvaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiValidatorTest {

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private GroupsRestrictionsEvaluator groupsRestrictionsEvaluator;

    @Mock
    private ProjectsInfoService projectsInfoService;

    @Mock
    private ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps catalogItemUserActionGroupsRestrictionProps;

    @Mock
    private MandatoryFieldsValidator mandatoryFieldsValidator;

    @InjectMocks
    private ProvisionerActionsApiValidator provisionerActionsApiValidator;

    @ParameterizedTest
    @ValueSource(strings = { "project_key", "component_id", "access_token"})
    void givenRequiredParameterMissing_whenValidating_thenThrowsInvalidRestEntityException(String missingParam) {
        //given
        var action = givenMissingParameterName_whenBuildingAction_thenReturnsActionWithoutMissingParameter(missingParam);

        //when //then
        assertThrows(InvalidRestEntityException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

    @Test
    void givenAlreadyProvisionedComponent_whenValidating_thenThrowsProjectComponentAlreadyProvisionedException() {
        //given
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

        var exists = new ProjectComponentInfo();
        exists.setComponentId(componentId);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        when(componentCatalogService.getProjectComponents(any(), any()))
                .thenReturn(List.of(exists));

        //when //then
        assertThrows(ProjectComponentAlreadyProvisionedException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

    @Test
    void givenUserWithoutPermissions_whenValidating_thenThrowsUserNotAllowedException() {
        //given
        var projectKey = "pkey";
        var componentId = "cid";
        var accessToken = "accessToken";

        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", projectKey),
                ProvisionActionParameterMother.of("component_id", componentId),
                ProvisionActionParameterMother.of("catalog_item_id", "111"),
                ProvisionActionParameterMother.of("access_token", accessToken),
                ProvisionActionParameterMother.of("workflow", "123")
        ));

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // Component catalog empty -> no conflict
        when(componentCatalogService.getProjectComponents(any(), any()))
                .thenReturn(List.of());

        // User groups
        when(projectsInfoService.getProjectGroups(accessToken))
                .thenReturn(List.of("group1"));

        // Configure restriction prefix/suffix
        when(catalogItemUserActionGroupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
        when(catalogItemUserActionGroupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));

        // Simulate evaluator result -> forbidden
        when(groupsRestrictionsEvaluator.evaluate(any(), any()))
                .thenReturn(Pair.of(false, "User is not allowed"));

        //when //then
        assertThrows(UserNotAllowedException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

    @Test
    void givenUserWithPermissions_whenValidating_thenDoesNotThrow() {
        //given
        var projectKey = "pkey";
        var componentId = "cid";
        var accessToken = "accessToken";

        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", projectKey),
                ProvisionActionParameterMother.of("component_id", componentId),
                ProvisionActionParameterMother.of("catalog_item_id", "111"),
                ProvisionActionParameterMother.of("access_token", accessToken),
                ProvisionActionParameterMother.of("workflow", "123")
        ));

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        // Component does NOT exist
        when(componentCatalogService.getProjectComponents(any(), any()))
                .thenReturn(List.of());

        // User groups
        when(projectsInfoService.getProjectGroups(accessToken))
                .thenReturn(List.of("allowed-group"));

        // Configure restriction prefix/suffix
        when(catalogItemUserActionGroupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
        when(catalogItemUserActionGroupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));

        // Simulate evaluator result -> allowed
        when(groupsRestrictionsEvaluator.evaluate(any(), any()))
                .thenReturn(Pair.of(true, ""));

        //when //then
        provisionerActionsApiValidator.validate(action);
    }

    @Test
    void givenBlankProjectKey_whenValidating_thenThrowsInvalidRestEntityException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", ""),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        //when //then
        assertThrows(InvalidRestEntityException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

    @Test
    void givenBlankComponentId_whenValidating_thenThrowsInvalidRestEntityException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", ""),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        //when //then
        assertThrows(InvalidRestEntityException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

    @Test
    void givenBlankAccessToken_whenValidating_thenThrowsInvalidRestEntityException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "")
        ));

        //when //then
        assertThrows(InvalidRestEntityException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

    @Test
    void givenMandatoryFieldsValidatorThrows_whenValidatingMandatoryFields_thenThrowsInvalidRestEntityException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        doThrow(new InvalidRestEntityException("Mandatory field missing"))
                .when(mandatoryFieldsValidator)
                .validate(any(), any());

        //when //then
        assertThrows(InvalidRestEntityException.class,
                () -> provisionerActionsApiValidator.validateMandatoryFields(action, new CatalogItem()));
    }

    @Test
    void givenComponentCatalogServiceThrows_whenValidating_thenThrowsRuntimeException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        when(authenticationProvider.getAccessToken()).thenReturn("bearerToken");
        when(componentCatalogService.getProjectComponents(any(), any())).thenThrow(new RuntimeException("Service error"));

        //when //then
        assertThrows(RuntimeException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

    @Test
    void givenProjectsInfoServiceThrows_whenValidating_thenThrowsRuntimeException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        //when //then
        assertThrows(RuntimeException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

    @Test
    void givenGroupsRestrictionsEvaluatorThrows_whenValidating_thenThrowsRuntimeException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        when(authenticationProvider.getAccessToken()).thenReturn("bearerToken");

        //when //then
        assertThrows(RuntimeException.class,
                () -> provisionerActionsApiValidator.validate(action));
    }

    private ProvisionAction givenMissingParameterName_whenBuildingAction_thenReturnsActionWithoutMissingParameter(String missingParamName) {
        //given
        var params = new ArrayList<ProvisionActionParameter>();
        //when
        if (!"project_key".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("project_key", "pkey"));
        if (!"component_id".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("component_id", "cid"));
        if (!"catalog_item_id".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("catalog_item_id", "catid"));
        if (!"access_token".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("access_token", "accessToken"));

        //then
        return ProvisionActionMother.of(params);
    }

    @Test
    void givenNullCatalogItem_whenValidatingReceivesOnlyVisibleParameters_thenThrowsInvalidRestEntityException() {
        //given
        var action = ProvisionActionMother.of(Collections.emptyList());

        //when //then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, null))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void givenCatalogItemWithoutUserActions_whenValidatingReceivesOnlyVisibleParameters_thenThrowsInvalidRestEntityException() {
        //given
        var catalogItem = CatalogItem.builder().title("My Item").userActions(null).build();
        var action = ProvisionActionMother.of(Collections.emptyList());

        //when //then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void givenCatalogItemWithoutProvisionUserAction_whenValidatingReceivesOnlyVisibleParameters_thenThrowsInvalidRestEntityException() {
        //given
        var userAction = CatalogItemUserAction.builder().id("DELETE").parameters(List.of()).build();
        var catalogItem = CatalogItem.builder().title("My Item").userActions(List.of(userAction)).build();
        var action = ProvisionActionMother.of(Collections.emptyList());

        //when //then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("doesn't have a PROVISION user action");
    }

    @Test
    void givenParameterNotDefinedInCatalog_whenValidatingReceivesOnlyVisibleParameters_thenThrowsInvalidRestEntityException() {
        //given
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

        //when //then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("unknown_param")
                .hasMessageContaining("My Catalog Item");
    }

    @Test
    void givenNotVisibleParameter_whenValidatingReceivesOnlyVisibleParameters_thenThrowsInvalidRestEntityException() {
        //given
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

        //when //then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("hidden_param")
                .hasMessageContaining("My Catalog Item");
    }

    @Test
    void givenParameterWithNullVisibility_whenValidatingReceivesOnlyVisibleParameters_thenThrowsInvalidRestEntityException() {
        //given
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

        //when //then
        assertThatThrownBy(() -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("null_visibility_param")
                .hasMessageContaining("My Catalog Item");
    }

    @Test
    void givenAllVisibleParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        //given
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

        //when //then
        assertThatNoException().isThrownBy(
                () -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem));
    }

    @Test
    void givenOnlyVisibleOptionalParameter_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        //given
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

        //when //then
        assertThatNoException().isThrownBy(
                () -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem));
    }

    @Test
    void givenNoParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        //given
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

        //when //then
        assertThatNoException().isThrownBy(
                () -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem));
    }

    @Test
    void givenOnlyInternalParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        //given
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

        //when //then
        assertThatNoException().isThrownBy(
                () -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem));
    }

    @Test
    void givenInternalAndVisibleParameters_whenValidatingReceivesOnlyVisibleParameters_thenDoesNotThrow() {
        //given
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

        //when //then
        assertThatNoException().isThrownBy(
                () -> provisionerActionsApiValidator.validateReceivesOnlyVisibleParameters(action, catalogItem));
    }

    @Test
    void givenMissingWorkflowAndWorkflowName_whenValidatingWorkflowPresence_thenThrowsInvalidRestEntityException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("access_token", "accessToken")
        ));

        //when //then
        assertThrows(InvalidRestEntityException.class,
                () -> provisionerActionsApiValidator.validateWorkflowPresence(action));
    }

    @Test
    void givenMissingDeletionWorkflowAndWorkflowProvided_whenValidatingWorkflowPresence_thenThrowsInvalidRestEntityException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow", "wf-123")
        ));

        //when //then
        assertThrows(InvalidRestEntityException.class,
                () -> provisionerActionsApiValidator.validateWorkflowPresence(action));
    }

    @Test
    void givenMissingDeletionWorkflowAndWorkflowNameProvided_whenValidatingWorkflowPresence_thenThrowsInvalidRestEntityException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow_name", "wf-name")
        ));

        //when //then
        assertThrows(InvalidRestEntityException.class,
                () -> provisionerActionsApiValidator.validateWorkflowPresence(action));
    }

    @Test
    void givenBlankDeletionWorkflow_whenValidatingWorkflowPresence_thenThrowsInvalidRestEntityException() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow", "wf-123"),
                ProvisionActionParameterMother.of("deletion_workflow", "   ")
        ));

        //when //then
        assertThrows(InvalidRestEntityException.class,
                () -> provisionerActionsApiValidator.validateWorkflowPresence(action));
    }

    @Test
    void givenWorkflowProvidedByUser_whenValidatingWorkflowPresence_thenDoesNotThrow() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow", "wf-123"),
                ProvisionActionParameterMother.of("deletion_workflow", "del-wf-from-hidden-param")
        ));

        //when //then
        assertThatNoException().isThrownBy(() -> provisionerActionsApiValidator.validateWorkflowPresence(action));
    }

    @Test
    void givenWorkflowNameProvidedByUser_whenValidatingWorkflowPresence_thenDoesNotThrow() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow_name", "wf-name"),
                ProvisionActionParameterMother.of("deletion_workflow", "del-wf-from-hidden-param")
        ));

        //when //then
        assertThatNoException().isThrownBy(() -> provisionerActionsApiValidator.validateWorkflowPresence(action));
    }

    @Test
    void givenWorkflowAndWorkflowNameProvided_whenValidatingWorkflowPresence_thenDoesNotThrow() {
        //given
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("workflow", "wf-123"),
                ProvisionActionParameterMother.of("workflow_name", "wf-name"),
                ProvisionActionParameterMother.of("deletion_workflow", "del-wf-from-hidden-param")
        ));

        //when //then
        assertThatNoException().isThrownBy(() -> provisionerActionsApiValidator.validateWorkflowPresence(action));
    }

    @Test
    void givenWorkflowFromHiddenCatalogItemParam_whenValidatingWorkflowPresence_thenDoesNotThrow() {
        //given
        // Workflow was not provided by the user but was injected from the catalog item's
        // hidden (non-visible) mandatory parameter before this validation is invoked
        var action = ProvisionActionMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "pkey"),
                ProvisionActionParameterMother.of("component_id", "cid"),
                ProvisionActionParameterMother.of("catalog_item_id", "catid"),
                ProvisionActionParameterMother.of("workflow", "wf-from-hidden-param"),
                ProvisionActionParameterMother.of("deletion_workflow", "del-wf-from-hidden-param")
        ));

        //when //then
        assertThatNoException().isThrownBy(() -> provisionerActionsApiValidator.validateWorkflowPresence(action));
    }
}
