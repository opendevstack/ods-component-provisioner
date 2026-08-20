package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserAction;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentParameter;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatus;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.mappers.CreateIncidentParameterMapper;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.model.ProjectComponentExtendedInfoMother;

import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionServiceTest {

    @Mock
    private ApiClientsBuilder apiClientsBuilder;

    @Mock
    private ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;

    @Mock
    private ProvisionerActionsApi provisionerActionsApi;

    @Mock
    private ApiClient apiClient;

    @Mock
    private CatalogItemsApi catalogItemsApi;

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private CreateIncidentParameterMapper createIncidentParameterMapper;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private EntitiesMapper entitiesMapper;

    @InjectMocks
    private ProvisionService provisionService;

    @Test
    void givenClientUpdateRequest_whenNotifyStatusUpdate_thenInvokesProvisionerActionsApiPut() throws Exception {
        // given
        var projectKey = "PRJ";
        var status =
                org.opendevstack.component_provisioner.server.model.ProvisioningStatus.CREATED;
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";

        var clientRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId("CID")
                .catalogItemId("CAT")
                .componentUrl("http://example.com")
                .build();

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.provisionerActionsApi(accessToken, baseUrl)).thenReturn(provisionerActionsApi);
        when(entitiesMapper.asProvisioningStatus(status)).thenReturn(ProvisioningStatus.CREATED);

        // when
        provisionService.notifyProvisioningStatusUpdate(projectKey, status, clientRequest, accessToken);

        // then
        verify(provisionerActionsApi)
                .notifyProvisioningStatusUpdate(projectKey, ProvisioningStatus.CREATED, clientRequest);
    }

    @Test
    void givenClientUpdateRequest_whenNotifyStatusUpdatePartially_thenInvokesApiPatch() throws Exception {
        // given
        var projectKey = "PRJ";
        var status =
                org.opendevstack.component_provisioner.server.model.ProvisioningStatus.CREATED;
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";

        var clientRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId("CID")
                .catalogItemId("CAT")
                .componentUrl("http://example.com")
                .build();

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.provisionerActionsApi(accessToken, baseUrl)).thenReturn(provisionerActionsApi);
        when(entitiesMapper.asProvisioningStatus(status)).thenReturn(ProvisioningStatus.CREATED);

        // when
        provisionService.notifyProvisioningStatusUpdatePartially(projectKey, status, clientRequest, accessToken);

        // then
        verify(provisionerActionsApi)
                .notifyProvisioningStatusUpdatePartially(projectKey, ProvisioningStatus.CREATED, clientRequest);
    }

    @Test
    void givenProjectComponent_whenGetDeletionParameters_thenReturnsMappedParameters() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();

        // Setup base64 encoded IDs to avoid InvalidIdException
        projectComponent.setCatalogItemId("Y2F0YWxvZ0l0ZW1JZA=="); // catalogItemId
        projectComponent.setCatalogItemRef("Y2F0YWxvZ0l0ZW1SZWY="); // catalogItemRef

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);
        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.catalogItemsApi(apiClient)).thenReturn(catalogItemsApi);

        CatalogItem catalogItem = new CatalogItem();
        var actionParam = CatalogItemUserActionParameter.builder()
                .name("param1")
                .sendOnDeletion(true)
                .build();
        catalogItem.setUserActions(List.of(CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(List.of(actionParam))
                .build()));
        when(catalogItemsApi.getCatalogItemById(any())).thenReturn(catalogItem);

        var projectParam = ProjectComponentParameter.builder()
                .name("param1")
                .values(List.of("value1"))
                .build();
        projectComponent.setParameters(List.of(projectParam));

        when(createIncidentParameterMapper.toTarget(actionParam, projectParam.getValues()))
                .thenReturn(CreateIncidentParameter.builder().name("param1").value("value1").build());

        // when
        var result = provisionService.getDeletionParameters(projectKey, componentId);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getName()).isEqualTo("param1");
        assertThat(result.getFirst().getValue()).isEqualTo("value1");
    }

    @Test
    void givenUserActionsIsNull_whenGetDeletionParameters_thenReturnsEmptyList() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();
        projectComponent.setCatalogItemId("Y2F0YWxvZ0l0ZW1JZA==");
        projectComponent.setCatalogItemRef("Y2F0YWxvZ0l0ZW1SZWY=");

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);
        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.catalogItemsApi(apiClient)).thenReturn(catalogItemsApi);

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setUserActions(null);
        when(catalogItemsApi.getCatalogItemById(any())).thenReturn(catalogItem);

        // when
        var result = provisionService.getDeletionParameters(projectKey, componentId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenActionParametersIsNull_whenGetDeletionParameters_thenReturnsEmptyList() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();
        projectComponent.setCatalogItemId("Y2F0YWxvZ0l0ZW1JZA==");
        projectComponent.setCatalogItemRef("Y2F0YWxvZ0l0ZW1SZWY=");

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);
        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.catalogItemsApi(apiClient)).thenReturn(catalogItemsApi);

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setUserActions(List.of(CatalogItemUserAction.builder()
                .parameters(null)
                .build()));
        when(catalogItemsApi.getCatalogItemById(any())).thenReturn(catalogItem);

        // when
        var result = provisionService.getDeletionParameters(projectKey, componentId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenSendOnDeletionIsFalse_whenGetDeletionParameters_thenReturnsEmptyList() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();
        projectComponent.setCatalogItemId("Y2F0YWxvZ0l0ZW1JZA==");
        projectComponent.setCatalogItemRef("Y2F0YWxvZ0l0ZW1SZWY=");

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);
        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.catalogItemsApi(apiClient)).thenReturn(catalogItemsApi);

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setUserActions(List.of(CatalogItemUserAction.builder()
                .parameters(List.of(CatalogItemUserActionParameter.builder()
                        .name("param1")
                        .sendOnDeletion(false)
                        .build()))
                .build()));
        when(catalogItemsApi.getCatalogItemById(any())).thenReturn(catalogItem);

        // when
        var result = provisionService.getDeletionParameters(projectKey, componentId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenProjectParametersIsNull_whenGetDeletionParameters_thenReturnsEmptyList() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();
        projectComponent.setCatalogItemId("Y2F0YWxvZ0l0ZW1JZA==");
        projectComponent.setCatalogItemRef("Y2F0YWxvZ0l0ZW1SZWY=");
        projectComponent.setParameters(null);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);
        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.catalogItemsApi(apiClient)).thenReturn(catalogItemsApi);

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setUserActions(List.of(CatalogItemUserAction.builder()
                .parameters(List.of(CatalogItemUserActionParameter.builder()
                        .name("param1")
                        .sendOnDeletion(true)
                        .build()))
                .build()));
        when(catalogItemsApi.getCatalogItemById(any())).thenReturn(catalogItem);

        // when
        var result = provisionService.getDeletionParameters(projectKey, componentId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenParamNameNotFoundInProject_whenGetDeletionParameters_thenReturnsEmptyList() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();
        projectComponent.setCatalogItemId("Y2F0YWxvZ0l0ZW1JZA==");
        projectComponent.setCatalogItemRef("Y2F0YWxvZ0l0ZW1SZWY=");

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);
        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.catalogItemsApi(apiClient)).thenReturn(catalogItemsApi);

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setUserActions(List.of(CatalogItemUserAction.builder()
                .parameters(List.of(CatalogItemUserActionParameter.builder()
                        .name("differentParam")
                        .sendOnDeletion(true)
                        .build()))
                .build()));
        when(catalogItemsApi.getCatalogItemById(any())).thenReturn(catalogItem);

        var projectParam = ProjectComponentParameter.builder()
                .name("param1")
                .values(List.of("value1"))
                .build();
        projectComponent.setParameters(List.of(projectParam));

        // when
        var result = provisionService.getDeletionParameters(projectKey, componentId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenProjectKeyAndComponentId_whenDeleteProvisioningStatus_thenInvokesProvisionerActionsApi() {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";

        // when
        provisionService.deleteProvisioningStatus(projectKey, componentId);

        // then
        var expectedRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        verify(provisionerActionsApi).deleteProvisioningStatus(projectKey, expectedRequest);
    }

    @Test
    void givenProjectComponentWithDeletionWorkflow_whenGetDeletionWorkflowIsCalled_thenReturnsWorkflow() {
        // given
        var accessToken = "bearerToken";
        var projectKey = "PRJ";
        var componentId = "CID";
        var projectComponent = new ProjectComponentExtendedInfo();
        var parameter = new ProjectComponentParameter();
        parameter.setName("deletion_workflow");
        parameter.setValues(List.of("WF_NAME"));
        projectComponent.setParameters(List.of(parameter));

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId)).thenReturn(projectComponent);

        // when
        var result = provisionService.getDeletionWorkflowId(projectKey, componentId);

        // then
        assertThat(result).isEqualTo("WF_NAME");
    }

    @Test
    void givenProjectComponentWithoutDeletionWorkflow_whenGetDeletionWorkflowIsCalled_thenReturnsEmptyString() {
        // given
        var accessToken = "bearerToken";
        var projectKey = "PRJ";
        var componentId = "CID";
        var projectComponent = new ProjectComponentExtendedInfo();
        projectComponent.setParameters(List.of());

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);

        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId)).thenReturn(projectComponent);

        // when
        var result = provisionService.getDeletionWorkflowId(projectKey, componentId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenProjectComponentWithDeletionWorkflowName_whenGetDeletionWorkflowNameIsCalled_thenReturnsWorkflowName() {
        // given
        var accessToken = "token";
        var projectKey = "PRJ";
        var componentId = "CID";

        var projectComponent = new ProjectComponentExtendedInfo();
        var parameter = new ProjectComponentParameter();
        parameter.setName("deletion_workflow_name");
        parameter.setValues(List.of("WF_NAME"));

        projectComponent.setParameters(List.of(parameter));

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);

        // when
        var result = provisionService.getDeletionWorkflowName(projectKey, componentId);

        // then
        assertThat(result).isEqualTo("WF_NAME");
    }

    @Test
    void givenProjectComponentWithoutDeletionWorkflowName_whenGetDeletionWorkflowNameIsCalled_thenReturnsEmptyString() {
        // given
        var accessToken = "token";
        var projectKey = "PRJ";
        var componentId = "CID";

        var projectComponent = new ProjectComponentExtendedInfo();
        projectComponent.setParameters(List.of());

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);

        // when
        var result = provisionService.getDeletionWorkflowName(projectKey, componentId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenProjectComponentWithDeletionWorkflowTimeout_whenGetDeletionWorkflowTimeoutIsCalled_thenReturnsTimeout() {
        // given
        var accessToken = "token";
        var projectKey = "PRJ";
        var componentId = "CID";

        var projectComponent = new ProjectComponentExtendedInfo();
        var parameter = new ProjectComponentParameter();
        parameter.setName("deletion_workflow_timeout_seconds");
        parameter.setValues(List.of("120"));

        projectComponent.setParameters(List.of(parameter));

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);

        // when
        var result = provisionService.getDeletionWorkflowTimeoutSeconds(projectKey, componentId);

        // then
        assertThat(result).isEqualTo("120");
    }

    @Test
    void givenNoWorkflowTimeout_whenGetDeletionWorkflowTimeout_thenReturnsEmptyString() {
        // given
        var accessToken = "token";
        var projectKey = "PRJ";
        var componentId = "CID";

        var projectComponent = new ProjectComponentExtendedInfo();
        projectComponent.setParameters(List.of());

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);

        // when
        var result = provisionService.getDeletionWorkflowTimeoutSeconds(projectKey, componentId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenProjectComponentWithAllDeletionWorkflowParams_whenEachGetterIsCalled_thenReturnsCorrectValues() {
        // given
        var accessToken = "token";
        var projectKey = "PRJ";
        var componentId = "CID";

        var params = List.of(
                ProjectComponentParameter.builder()
                        .name("deletion_workflow")
                        .values(List.of("WF_ID"))
                        .build(),
                ProjectComponentParameter.builder()
                        .name("deletion_workflow_name")
                        .values(List.of("WF_NAME"))
                        .build(),
                ProjectComponentParameter.builder()
                        .name("deletion_workflow_timeout_seconds")
                        .values(List.of("300"))
                        .build()
        );

        var projectComponent = new ProjectComponentExtendedInfo();
        projectComponent.setParameters(params);

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);

        // when / then
        assertThat(provisionService.getDeletionWorkflowId(projectKey, componentId)).isEqualTo("WF_ID");
        assertThat(provisionService.getDeletionWorkflowName(projectKey, componentId)).isEqualTo("WF_NAME");
        assertThat(provisionService.getDeletionWorkflowTimeoutSeconds(projectKey, componentId)).isEqualTo("300");
    }

    @Test
    void givenParameterWithNullValues_whenGetDeletionWorkflowIsCalled_thenThrowsException() {
        // given
        var accessToken = "token";
        var projectKey = "PRJ";
        var componentId = "CID";

        var parameter = new ProjectComponentParameter();
        parameter.setName("deletion_workflow_name");
        parameter.setValues(null);

        var projectComponent = new ProjectComponentExtendedInfo();
        projectComponent.setParameters(List.of(parameter));

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(projectComponent);

        // when / then
        assertThatThrownBy(() ->
                provisionService.getDeletionWorkflowName(projectKey, componentId)
        ).isInstanceOf(AssertionError.class);
    }


}
