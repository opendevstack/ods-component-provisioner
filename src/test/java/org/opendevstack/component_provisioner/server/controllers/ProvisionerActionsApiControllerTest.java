package org.opendevstack.component_provisioner.server.controllers;

import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectComponentAlreadyProvisionedException;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.*;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;
import org.opendevstack.component_provisioner.server.security.AuthorizationInfo;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiControllerTest {

    @Mock
    private AuthorizationInfo authInfo;

    @Mock
    private AwxService awxService;

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private EntitiesMapper entitiesMapper;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @InjectMocks
    private ProvisionerActionsApiController controller;

    @BeforeEach
    void setUp() {
        when(authenticationProvider.getIdToken()).thenReturn("idToken");
    }

    @Test
    void triggerProvisionAction_returnsResponseEntityWithMappedResponse_whenServiceReturnsSuccess() {
        var projectKey = "projectKey";
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var accessToken = "accessToken";
        var componentUrl = "componentUrl";

        var parameters = new ArrayList<ProvisionActionParameter>();
        parameters.add(ProvisionActionParameterMother.of("project_key", projectKey));
        parameters.add(ProvisionActionParameterMother.of("component_id", componentId));
        parameters.add(ProvisionActionParameterMother.of("catalog_item_id", catalogItemId));
        parameters.add(ProvisionActionParameterMother.of("access_token", accessToken));
        parameters.add(ProvisionActionParameterMother.of("component_url", componentUrl));

        var provisionAction = ProvisionActionMother.of(parameters);

        var workflowJobLaunch = new AwxWorkflowJobLaunch();
        var provisionActionResponse = new ProvisionActionResponse();

        when(authInfo.getCurrentPrincipalName()).thenReturn("test-user");
        when(entitiesMapper.asAwxWorkflowJobLaunch(provisionAction)).thenReturn(workflowJobLaunch);
        when(awxService.triggerWorkflowJob("action-id", workflowJobLaunch))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(new AwxWorkflowJob())));
        when(entitiesMapper.asProvisionActionResponse(any(AwxWorkflowJob.class))).thenReturn(provisionActionResponse);

        var response = controller.triggerProvisionAction(provisionAction);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(provisionActionResponse, response.getBody());

        verify(componentCatalogService)
                .notifyComponentCatalogProvisionStarts(eq(projectKey), eq(componentId), eq(catalogItemId), eq(componentUrl), any(java.util.Map.class));
    }

    @Test
    void triggerProvisionAction_returnsResponseEntityWithNullBody_whenServiceReturnsEmptyResponse() {
        var projectKey = "projectKey";
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var accessToken = "accessToken";

        // Mind that listOf, or Arrays.asList returns an immutable list
        var parameters = new ArrayList<ProvisionActionParameter>();
        parameters.add(ProvisionActionParameterMother.of("project_key", projectKey));
        parameters.add(ProvisionActionParameterMother.of("component_id", componentId));
        parameters.add(ProvisionActionParameterMother.of("catalog_item_id", catalogItemId));
        parameters.add(ProvisionActionParameterMother.of("access_token", accessToken));

        var provisionAction = ProvisionActionMother.of(parameters);

        var workflowJobLaunch = new AwxWorkflowJobLaunch();

        when(authInfo.getCurrentPrincipalName()).thenReturn("test-user");
        when(entitiesMapper.asAwxWorkflowJobLaunch(provisionAction)).thenReturn(workflowJobLaunch);
        when(awxService.triggerWorkflowJob("action-id", workflowJobLaunch))
                .thenReturn(Pair.of(HttpStatus.NO_CONTENT, Optional.empty()));

        var response = controller.triggerProvisionAction(provisionAction);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @ValueSource(strings = { "project_key", "component_id", "access_token" })
    void validate_throwsInvalidRestEntityException_whenRequiredParameterMissing(String missingParam) {
        var action = buildActionMissing(missingParam);

        assertThrows(InvalidRestEntityException.class,
                () -> controller.triggerProvisionAction(action));
    }

    @Test
    void validate_throwsProjectComponentAlreadyProvisionedException_whenComponentAlreadyExistsInCatalog() {
        var projectKey = "pkey";
        var componentId = "cid";
        var idToken = "idToken";
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

        when(componentCatalogService.getProjectComponents(projectKey, idToken, accessToken))
                .thenReturn(List.of(exists));

        assertThrows(ProjectComponentAlreadyProvisionedException.class,
                () -> controller.triggerProvisionAction(action));
    }

    private ProvisionAction buildActionMissing(String missingParamName) {
        var params = new ArrayList<ProvisionActionParameter>();
        if (!"project_key".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("project_key", "pkey"));
        if (!"component_id".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("component_id", "cid"));
        if (!"catalog_item_id".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("catalog_item_id", "catid"));
        if (!"access_token".equals(missingParamName))
            params.add(ProvisionActionParameterMother.of("access_token", "accessToken"));

        return ProvisionActionMother.of(params);
    }
}