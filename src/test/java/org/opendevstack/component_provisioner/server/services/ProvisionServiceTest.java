package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.*;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.mappers.CreateIncidentParameterMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.model.ProjectComponentExtendedInfoMother;

import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @InjectMocks
    private ProvisionService provisionService;

    @Test
    void givenAClientUpdateRequest_whenNotifyProvisioningStatusUpdateIsCalled_thenInvokesProvisionerActionsApiPut() throws Exception {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";

        var clientRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId("CID")
                .catalogItemId("CAT")
                .componentUrl("http://example.com")
                .build();

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.provisionerActionsApi(accessToken, baseUrl)).thenReturn(provisionerActionsApi);

        // when
        provisionService.notifyProvisioningStatusUpdate(projectKey, status, clientRequest, accessToken);

        // then
        verify(provisionerActionsApi).notifyProvisioningStatusUpdate(projectKey, status.name(), clientRequest);
    }

    @Test
    void givenAClientUpdateRequest_whenNotifyProvisioningStatusUpdatePartiallyIsCalled_thenInvokesProvisionerActionsApiPatch() throws Exception {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";

        var clientRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId("CID")
                .catalogItemId("CAT")
                .componentUrl("http://example.com")
                .build();

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.provisionerActionsApi(accessToken, baseUrl)).thenReturn(provisionerActionsApi);

        // when
        provisionService.notifyProvisioningStatusUpdatePartially(projectKey, status, clientRequest, accessToken);

        // then
        verify(provisionerActionsApi).notifyProvisioningStatusUpdatePartially(projectKey, status.name(), clientRequest);
    }

    @Test
    void givenAProjectKeyAndComponentIdAndAccessToken_whenGetDeletionParametersIsCalled_thenReturnsMappedParameters() throws Exception {
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
        when(componentCatalogService.getProjectComponentExtendedInfo(projectKey, componentId))
                .thenReturn(projectComponent);
        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.catalogItemsApi(apiClient)).thenReturn(catalogItemsApi);

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setUserActions(List.of(CatalogItemUserAction.builder()
                .id("PROVISION")
                .parameters(List.of(CatalogItemUserActionParameter.builder()
                        .name("param1")
                        .sendOnDeletion(true)
                        .build()))
                .build()));
        when(catalogItemsApi.getCatalogItemById(any())).thenReturn(catalogItem);

        var projectParam = ProjectComponentParameter.builder()
                .name("param1")
                .values(List.of("value1"))
                .build();
        projectComponent.setParameters(List.of(projectParam));

        when(createIncidentParameterMapper.toTarget(projectParam))
                .thenReturn(CreateIncidentParameter.builder().name("param1").value("value1").build());

        // when
        var result = provisionService.getDeletionParameters(projectKey, componentId);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("param1");
        assertThat(result.get(0).getValue()).isEqualTo("value1");
    }

    @Test
    void givenAProjectKeyAndComponentIdAndAccessToken_whenGetDeletionParametersIsCalledAndUserActionsIsNull_thenReturnsEmptyList() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();
        projectComponent.setCatalogItemId("Y2F0YWxvZ0l0ZW1JZA==");
        projectComponent.setCatalogItemRef("Y2F0YWxvZ0l0ZW1SZWY=");

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentExtendedInfo(projectKey, componentId))
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
    void givenAProjectKeyAndComponentIdAndAccessToken_whenGetDeletionParametersIsCalledAndActionParametersIsNull_thenReturnsEmptyList() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();
        projectComponent.setCatalogItemId("Y2F0YWxvZ0l0ZW1JZA==");
        projectComponent.setCatalogItemRef("Y2F0YWxvZ0l0ZW1SZWY=");

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentExtendedInfo(projectKey, componentId))
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
    void givenAProjectKeyAndComponentIdAndAccessToken_whenGetDeletionParametersIsCalledAndSendOnDeletionIsFalse_thenReturnsEmptyList() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();
        projectComponent.setCatalogItemId("Y2F0YWxvZ0l0ZW1JZA==");
        projectComponent.setCatalogItemRef("Y2F0YWxvZ0l0ZW1SZWY=");

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentExtendedInfo(projectKey, componentId))
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
    void givenAProjectKeyAndComponentIdAndAccessToken_whenGetDeletionParametersIsCalledAndProjectParametersIsNull_thenReturnsEmptyList() throws Exception {
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
        when(componentCatalogService.getProjectComponentExtendedInfo(projectKey, componentId))
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
    void givenAProjectKeyAndComponentIdAndAccessToken_whenGetDeletionParametersIsCalledAndParamNameNotFoundInProject_thenReturnsEmptyList() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();
        projectComponent.setCatalogItemId("Y2F0YWxvZ0l0ZW1JZA==");
        projectComponent.setCatalogItemRef("Y2F0YWxvZ0l0ZW1SZWY=");

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentExtendedInfo(projectKey, componentId))
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
    void givenAProjectKeyAndComponentIdAndAccessToken_whenDeleteProvisioningStatusIsCalled_thenInvokesProvisionerActionsApi() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.provisionerActionsApi(accessToken, baseUrl)).thenReturn(provisionerActionsApi);

        // when
        provisionService.deleteProvisioningStatus(projectKey, componentId, accessToken);

        // then
        var expectedRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        verify(provisionerActionsApi).deleteProvisioningStatus(projectKey, expectedRequest);
    }
}
