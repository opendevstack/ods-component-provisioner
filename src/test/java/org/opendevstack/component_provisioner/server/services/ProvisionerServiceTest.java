package org.opendevstack.component_provisioner.server.services;

import org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.mappers.ProvisioningStatusUpdateRequestParametersInnerMapper;
import org.opendevstack.component_provisioner.server.model.ProjectComponentExtendedInfoMother;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerServiceTest {

    @Mock
    private ProvisionerActionsApi provisionerActionsApi;

    @Mock
    private ApiClientsBuilder apiClientsBuilder;

    @Mock
    private ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;

    @Mock
    private ApiClient apiClient;

    @Mock
    private CatalogItemsApi catalogItemsApi;

    @Mock
    private ProvisioningStatusUpdateRequestParametersInnerMapper provisioningStatusUpdateRequestParametersInnerMapper;

    @InjectMocks
    private ProvisionService provisionService;

    @Test
    void givenAProjectKeyAndStatusAndComponentIdAndCatalogItemIdAndComponentUrlAndAccessToken_whenNotifyProvisioningStatusUpdateIsCalled_thenInvokesProvisionerActionsApi() throws java.net.MalformedURLException {
        // given
        var projectKey = "projectKey";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";
        var accessToken = "accessToken";
        var baseUrl = "http://localhost";

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(java.net.URI.create(baseUrl).toURL());
        when(apiClientsBuilder.provisionerActionsApi(eq(accessToken), eq(baseUrl))).thenReturn(provisionerActionsApi);

        // when
        provisionService.notifyProvisioningStatusUpdate(projectKey, status, componentId, catalogItemId, componentUrl, accessToken);

        // then
        var expectedRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .build();

        verify(provisionerActionsApi).notifyProvisioningStatusUpdatePartially(projectKey, status.name(), expectedRequest);
    }

    @Test
    void givenAProjectKeyAndComponentIdAndAccessToken_whenDeleteProvisioningStatusIsCalled_thenInvokesProvisionerActionsApi() throws java.net.MalformedURLException {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";
        var baseUrl = "http://localhost";
        var accessToken = "accessToken";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(java.net.URI.create(baseUrl).toURL());
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

        when(apiClientsBuilder.provisionerActionsApi(eq(accessToken), eq(baseUrl))).thenReturn(provisionerActionsApi);
        when(provisioningStatusUpdateRequestParametersInnerMapper.toTarget(any()))
                .thenReturn(new ProvisioningStatusUpdateRequestParametersInner());

        projectComponent.setParameters(List.of(ProjectComponentParameter.builder()
                .name("param1")
                .values(List.of("value1"))
                .build()));

        // when
        provisionService.deleteProvisioningStatus(projectKey, componentId, projectComponent, accessToken);

        // then
        var expectedRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .parameters(List.of(new ProvisioningStatusUpdateRequestParametersInner()))
                .build();

        verify(provisionerActionsApi).deleteProvisioningStatus(projectKey, expectedRequest);
    }
}
