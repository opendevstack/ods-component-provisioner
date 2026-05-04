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
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.server.controllers.exceptions.SlugNotFoundException;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusPartialUpdateRequest;
import org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest;
import org.springframework.web.client.RestClientException;
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
    void givenAProjectKeyAndAComponentId_whenRequestProvisionToAwxIsCalled_thenMapsResponseCorrectly() {
        // given
        var action = CreateIncidentActionMother.of();
        var launch = new AwxWorkflowJobLaunch();
        var job = new AwxWorkflowJob();
        var response = new ProvisionActionResponse();

        when(entitiesMapper.asAwxWorkflowJobLaunch(action)).thenReturn(launch);
        when(awxService.triggerWorkflowJob("CREATE_INCIDENT", launch)).thenReturn(Pair.of(HttpStatus.OK, Optional.of(job)));
        when(entitiesMapper.asProvisionActionResponse(job)).thenReturn(response);

        // when
        var result = facade.requestProvisionToAwx("PRJ", "CID", action);

        // then
        assertEquals(HttpStatus.OK, result.httpStatusCode());
        assertEquals(response, result.awxResponseBody());
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenIsInDeletingStateIsCalled_thenReturnsTrueWhenMatchingComponentFound() {
        // given
        var action = CreateIncidentActionMother.of();
        var accessToken = action.getParameters().stream().filter(p -> p.getName().equals("access_token")).map(CreateIncidentParameter::getValue).map(Object::toString).findFirst().orElseThrow();
        ProjectComponentInfo pc = ProjectComponentInfoMother.of(ProjectComponentStatus.DELETING);
        when(componentCatalogService.getProjectComponents("PRJ", accessToken)).thenReturn(List.of(pc));

        // when
        var result = facade.isInDeletingState("PRJ", "componentId", accessToken);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void givenAnInvalidStatus_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = "PRJ";
        var status = "invalid";

        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate(projectKey, status));
        assertThat(ex.getMessage()).contains("Status is not valid");
    }

    @Test
    void givenAMissingProjectKeyOrStatus_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var projectKey = (String) null;
        var status = "CREATED";

        // when / then
        assertThrows(InvalidRestEntityException.class, () -> facade.validate(projectKey, status));
        assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", null));
    }

    @Test
    void givenAMissingMainParams_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var action = CreateIncidentActionMother.of();

        // when / then
        assertThrows(InvalidRestEntityException.class, () -> facade.validate(null, "CID", action));
        assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", null, action));
    }

    @Test
    void givenAMissingParameter_whenGetParameterStringIsCalled_thenReturnsEmptyString() {
        // given
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();

        // when
        var result = facade.getParameterString(action, "missing");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenIsInDeletingStateIsCalled_thenReturnsFalseWhenComponentNotFound() {
        // given
        var action = CreateIncidentActionMother.of();
        String accessToken = facade.getParameterString(action, "access_token");
        when(componentCatalogService.getProjectComponents("PRJ", accessToken)).thenReturn(Collections.emptyList());

        // when
        var result = facade.isInDeletingState("PRJ", "componentId", accessToken);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenIsInDeletingStateIsCalled_thenReturnsFalseWhenComponentNotDeleting() {
        // given
        var action = CreateIncidentActionMother.of();
        String accessToken = facade.getParameterString(action, "access_token");
        ProjectComponentInfo pc = ProjectComponentInfoMother.of(ProjectComponentStatus.CREATED);
        pc.setComponentId("componentId");
        when(componentCatalogService.getProjectComponents("PRJ", accessToken)).thenReturn(List.of(pc));

        // when
        var result = facade.isInDeletingState("PRJ", "componentId", accessToken);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void givenAValidStatus_whenValidateIsCalled_thenDoesNotThrow() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED.name();

        // when / then
        assertDoesNotThrow(() -> facade.validate(projectKey, status));
    }

    @Test
    void givenAMissingExtraParams_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // given
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();

        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "CID", action));
        assertThat(ex.getMessage()).contains("is_deployed, change_number and reason are required");
    }

    @Test
    void givenAnEmptyAwxResponse_whenRequestProvisionToAwxIsCalled_thenReturnsNullBody() {
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
    void givenAProvisionService_whenNotifyProvisioningStatusUpdateIsCalled_thenDelegatesToProvisionService() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemId = "CAT";
        var catalogItemSlug = "SLUG";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemId(catalogItemId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        var clientRequest = new org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest();
        when(entitiesMapper.asClientProvisioningStatusUpdateRequest(request)).thenReturn(clientRequest);

        // when
        facade.notifyProvisioningStatusUpdate(projectKey, status, request, accessToken);

        // then
        verify(provisionService).notifyProvisioningStatusUpdate(projectKey, status, clientRequest, accessToken);
        assertThat(request.getCatalogItemId()).isEqualTo(catalogItemId);
        assertThat(request.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenACatalogItemSlug_whenNotifyProvisioningStatusUpdateIsCalled_thenResolvesSlugToId() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemSlug = "SLUG";
        var resolvedCatalogItemId = "RESOLVED_ID";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        var catalogItem = new CatalogItem();
        catalogItem.setId(resolvedCatalogItemId);

        var clientRequest = new org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest();

        when(componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug)).thenReturn(catalogItem);
        when(entitiesMapper.asClientProvisioningStatusUpdateRequest(request)).thenReturn(clientRequest);

        // when
        facade.notifyProvisioningStatusUpdate(projectKey, status, request, accessToken);

        // then
        verify(provisionService).notifyProvisioningStatusUpdate(projectKey, status, clientRequest, accessToken);
        assertThat(request.getCatalogItemId()).isEqualTo(resolvedCatalogItemId);
        assertThat(request.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenAnInvalidCatalogItemSlug_whenNotifyProvisioningStatusUpdateIsCalled_thenThrowsSlugNotFoundException() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemSlug = "INVALID_SLUG";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        var request = new ProvisioningStatusUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        when(componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug)).thenThrow(new RestClientException("Not found"));

        // when / then
        assertThrows(SlugNotFoundException.class, () -> facade.notifyProvisioningStatusUpdate(projectKey, status, request, accessToken));
    }

    @Test
    void givenAProvisionService_whenNotifyProvisioningStatusUpdatePartiallyIsCalled_thenDelegatesToProvisionService() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemId = "CAT";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        var request = new ProvisioningStatusPartialUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemId(catalogItemId);
        request.componentUrl(componentUrl);

        var clientRequest = new org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest();
        when(entitiesMapper.asClientProvisioningStatusUpdateRequest(request)).thenReturn(clientRequest);

        // when
        facade.notifyProvisioningStatusUpdatePartially(projectKey, status, request, accessToken);

        // then
        verify(provisionService).notifyProvisioningStatusUpdatePartially(projectKey, status, clientRequest, accessToken);
        assertThat(request.getCatalogItemId()).isEqualTo(catalogItemId);
        assertThat(request.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenACatalogItemSlug_whenNotifyProvisioningStatusUpdatePartiallyIsCalled_thenResolvesSlugToId() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemSlug = "SLUG";
        var resolvedCatalogItemId = "RESOLVED_ID";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        var request = new ProvisioningStatusPartialUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        var catalogItem = new CatalogItem();
        catalogItem.setId(resolvedCatalogItemId);

        var clientRequest = new org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest();

        when(componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug)).thenReturn(catalogItem);
        when(entitiesMapper.asClientProvisioningStatusUpdateRequest(request)).thenReturn(clientRequest);

        // when
        facade.notifyProvisioningStatusUpdatePartially(projectKey, status, request, accessToken);

        // then
        verify(provisionService).notifyProvisioningStatusUpdatePartially(projectKey, status, clientRequest, accessToken);
        assertThat(request.getCatalogItemId()).isEqualTo(resolvedCatalogItemId);
        assertThat(request.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenAnInvalidCatalogItemSlug_whenNotifyProvisioningStatusUpdatePartiallyIsCalled_thenThrowsSlugNotFoundException() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemSlug = "INVALID_SLUG";
        var componentUrl = "http://example.com";
        var accessToken = "token";

        var request = new ProvisioningStatusPartialUpdateRequest();
        request.setComponentId(componentId);
        request.setCatalogItemSlug(catalogItemSlug);
        request.componentUrl(componentUrl);

        when(componentCatalogService.getCatalogItemBySlug(accessToken, catalogItemSlug)).thenThrow(new RestClientException("Not found"));

        // when / then
        assertThrows(SlugNotFoundException.class, () -> facade.notifyProvisioningStatusUpdatePartially(projectKey, status, request, accessToken));
    }

    @Test
    void givenBothCatalogItemIdAndSlug_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "CREATED", "ID", "SLUG"));
        assertThat(ex.getMessage()).contains("Both catalogItemId and catalogItemSlug cannot be defined at the same time");
    }

    @Test
    void givenOnlyCatalogItemId_whenValidateIsCalled_thenDoesNotThrow() {
        // when / then
        assertDoesNotThrow(() -> facade.validate("PRJ", "CREATED", "ID", null));
    }

    @Test
    void givenOnlyCatalogItemSlug_whenValidateIsCalled_thenDoesNotThrow() {
        // when / then
        assertDoesNotThrow(() -> facade.validate("PRJ", "CREATED", null, "SLUG"));
    }

    @Test
    void givenNeitherCatalogItemIdNorCatalogItemSlug_whenValidateIsCalled_thenThrowsInvalidRestEntityException() {
        // when
        var call = (org.junit.jupiter.api.function.Executable) () -> facade.validate("PRJ", "CREATED", null, null);

        // then
        var exception = assertThrows(InvalidRestEntityException.class, call);
        assertThat(exception.getMessage()).isEqualTo("Either catalogItemId or catalogItemSlug must be defined.");
    }

    @Test
    void givenAValidStatusAndRequest_whenValidateIsCalled_thenDoesNotThrow() {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED.name();

        // when / then
        assertDoesNotThrow(() -> facade.validate(projectKey, status, "ID", null));
    }

    @Test
    void givenAProjectKeyAndAComponentId_whenDeleteProvisioningStatusIsCalled_thenDelegatesToProvisionService() {
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
    void givenAProjectKeyAndAnAction_whenAddSystemParametersToActionIsCalled_thenAddsClusterAndCallerToAction() {
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
    void givenAProjectWithNoClusters_whenAddSystemParametersToActionIsCalled_thenThrowsProjectConfigurationException() {
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
