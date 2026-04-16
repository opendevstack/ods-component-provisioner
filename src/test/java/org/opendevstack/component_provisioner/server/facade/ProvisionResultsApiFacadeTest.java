package org.opendevstack.component_provisioner.server.facade;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfoMother;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectConfigurationException;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.CreateIncidentActionMother;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.ProjectsInfoService;
import org.opendevstack.component_provisioner.server.services.ProvisionService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionResultsApiFacadeTest {

    @Mock
    private AwxService awxService;
    @Mock
    private ComponentCatalogService componentCatalogService;
    @Mock
    private EntitiesMapper entitiesMapper;
    @Mock
    private ProvisionService provisionService;
    @Mock
    private AuthenticationProvider authenticationProvider;
    @Mock
    private ProjectsInfoService projectsInfoService;

    @InjectMocks
    private ProvisionResultsApiFacade facade;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(facade, "workflowJobId", "WORKFLOW_123");
    }

    @Test
    void requestProvisionToAwx_mapsResponseCorrectly() {
        var action = CreateIncidentActionMother.of();
        var launch = new AwxWorkflowJobLaunch();
        var job = new AwxWorkflowJob();
        var response = new ProvisionActionResponse();

        when(entitiesMapper.asAwxWorkflowJobLaunch(action)).thenReturn(launch);
        when(awxService.triggerWorkflowJob("CREATE_INCIDENT", launch)).thenReturn(Pair.of(HttpStatus.OK, Optional.of(job)));
        when(entitiesMapper.asProvisionActionResponse(job)).thenReturn(response);

        var result = facade.requestProvisionToAwx("PRJ", "CID", action);

        assertEquals(HttpStatus.OK, result.httpStatusCode());
        assertEquals(response, result.awxResponseBody());
    }

    @Test
    void isInDeletingState_returnsTrueWhenMatchingComponentFound() {
        var action = CreateIncidentActionMother.of();
        var accessToken = action.getParameters().stream().filter(p -> p.getName().equals("access_token")).map(CreateIncidentParameter::getValue).map(Object::toString).findFirst().orElseThrow();
        ProjectComponentInfo pc = ProjectComponentInfoMother.of(ProjectComponentStatus.DELETING);
        when(componentCatalogService.getProjectComponents("PRJ", accessToken)).thenReturn(List.of(pc));

        var result = facade.isInDeletingState("PRJ", "componentId", accessToken);
        assertThat(result).isTrue();
    }

    @Test
    void validate_status_throwsOnInvalid() {
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "invalid"));
        assertThat(ex.getMessage()).contains("Status is not valid");
    }

    @Test
    void validate_throwsOnMissingProjectKeyOrStatus() {
        assertThrows(InvalidRestEntityException.class, () -> facade.validate(null, "CREATED"));
        assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", null));
    }

    @Test
    void validate_createIncident_throwsOnMissingMainParams() {
        var action = CreateIncidentActionMother.of();
        assertThrows(InvalidRestEntityException.class, () -> facade.validate(null, "CID", action));
        assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", null, action));
    }

    @Test
    void getParameterString_returnsEmptyOnMissing() {
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();
        assertThat(facade.getParameterString(action, "missing")).isEmpty();
    }

    @Test
    void isInDeletingState_returnsFalseWhenComponentNotFound() {
        var action = CreateIncidentActionMother.of();
        String accessToken = facade.getParameterString(action, "access_token");
        when(componentCatalogService.getProjectComponents("PRJ", accessToken)).thenReturn(Collections.emptyList());

        var result = facade.isInDeletingState("PRJ", "componentId", accessToken);
        assertThat(result).isFalse();
    }

    @Test
    void isInDeletingState_returnsFalseWhenComponentNotDeleting() {
        var action = CreateIncidentActionMother.of();
        String accessToken = facade.getParameterString(action, "access_token");
        ProjectComponentInfo pc = ProjectComponentInfoMother.of(ProjectComponentStatus.CREATED);
        pc.setComponentId("componentId");
        when(componentCatalogService.getProjectComponents("PRJ", accessToken)).thenReturn(List.of(pc));

        var result = facade.isInDeletingState("PRJ", "componentId", accessToken);
        assertThat(result).isFalse();
    }

    @Test
    void validate_status_doesNotThrowWhenValid() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED.name();

        // when / then
        assertDoesNotThrow(() -> facade.validate(projectKey, status));
    }

    @Test
    void validate_createIncident_throwsOnMissingExtraParams() {
        // given
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();

        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "CID", action));
        assertThat(ex.getMessage()).contains("is_deployed, change_number and reason are required");
    }

    @Test
    void requestProvisionToAwx_returnsNullBodyWhenAwxResponseIsEmpty() {
        // given
        var action = CreateIncidentActionMother.of();
        var launch = new AwxWorkflowJobLaunch();

        when(entitiesMapper.asAwxWorkflowJobLaunch(action)).thenReturn(launch);
        when(awxService.triggerWorkflowJob("CREATE_INCIDENT", launch)).thenReturn(Pair.of(HttpStatus.ACCEPTED, Optional.empty()));

        // when
        var result = facade.requestProvisionToAwx("PRJ", "CID", action);

        // then
        assertEquals(HttpStatus.ACCEPTED, result.httpStatusCode());
        assertThat(result.awxResponseBody()).isNull();
    }

    @Test
    void notifyProvisioningStatusUpdate_delegatesToProvisionService() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemId = "CAT";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        // when
        facade.notifyProvisioningStatusUpdate(projectKey, status, componentId, catalogItemId, componentUrl, accessToken);

        // then
        verify(provisionService).notifyProvisioningStatusUpdate(projectKey, status, componentId, catalogItemId, componentUrl, accessToken);
    }

    @Test
    void deleteProvisioningStatus_delegatesToProvisionService() {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";

        // when
        facade.deleteProvisioningStatus(projectKey, componentId, accessToken);

        // then
        verify(provisionService).deleteProvisioningStatus(projectKey, componentId, accessToken);
    }

    @Test
    void addSystemParametersToAction_addsClusterAndCallerToAction() {
        // given
        var projectKey = "PRJ";
        var accessToken = "token123";
        var clusterLocation = "cluster-a";
        var caller = "user@example.com";
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();

        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(List.of(clusterLocation));

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(authenticationProvider.getUserPrincipalName()).thenReturn(caller);
        when(projectsInfoService.getProjectClusters(accessToken, projectKey)).thenReturn(projectInfo);

        // when
        facade.addSystemParametersToAction(projectKey, action);

        // then
        assertThat(facade.getParameterString(action, "cluster_location")).isEqualTo(clusterLocation);
        assertThat(facade.getParameterString(action, "caller")).isEqualTo(caller);
    }

    @Test
    void addSystemParametersToAction_throwsWhenClustersEmpty() {
        // given
        var projectKey = "PRJ";
        var accessToken = "token123";
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();

        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(Collections.emptyList());

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(projectsInfoService.getProjectClusters(accessToken, projectKey)).thenReturn(projectInfo);

        // when / then
        var ex = assertThrows(ProjectConfigurationException.class, () -> facade.addSystemParametersToAction(projectKey, action));
        assertThat(ex.getMessage()).contains("PRJ");
    }
}
