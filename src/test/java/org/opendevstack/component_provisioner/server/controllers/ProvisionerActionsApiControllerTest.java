package org.opendevstack.component_provisioner.server.controllers;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.security.AuthorizationInfo;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    ProvisionerActionsApiValidator provisionerActionsApiValidator;

    @InjectMocks
    private ProvisionerActionsApiController controller;

    @Test
    void triggerProvisionAction_returnsResponseEntityWithMappedResponse_whenServiceReturnsSuccess() {
        var projectKey = "projectKey";
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var idToken = "idToken";
        var accessToken = "accessToken";
        var componentUrl = "componentUrl";

        var parameters = new ArrayList<ProvisionActionParameter>();
        parameters.add(ProvisionActionParameterMother.of("project_key", projectKey));
        parameters.add(ProvisionActionParameterMother.of("component_id", componentId));
        parameters.add(ProvisionActionParameterMother.of("catalog_item_id", catalogItemId));
        parameters.add(ProvisionActionParameterMother.of("access_token", accessToken));
        parameters.add(ProvisionActionParameterMother.of("component_url", componentUrl));
        parameters.add(ProvisionActionParameterMother.of("list_param", List.of("v1", "v2")));
        parameters.add(ProvisionActionParameterMother.of("null_param", null));

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

        ArgumentCaptor<Map<String, List<String>>> paramsCaptor = ArgumentCaptor.forClass(Map.class);
        verify(componentCatalogService)
                .notifyComponentCatalogProvisionStarts(eq(projectKey), eq(componentId), eq(catalogItemId), eq(componentUrl), eq(idToken), eq(accessToken), paramsCaptor.capture());

        Map<String, List<String>> capturedParams = paramsCaptor.getValue();
        assertThat(capturedParams.get("project_key")).containsExactly(projectKey);
        assertThat(capturedParams.get("list_param")).containsExactly("v1", "v2");
        assertThat(capturedParams.get("null_param")).containsExactly("");
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
}