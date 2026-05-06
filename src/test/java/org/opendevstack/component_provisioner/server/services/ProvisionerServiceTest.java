package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.mappers.ProvisioningStatusUpdateRequestParametersInnerMapper;

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

        var clientRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .build();

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(java.net.URI.create(baseUrl).toURL());
        when(apiClientsBuilder.provisionerActionsApi(eq(accessToken), eq(baseUrl))).thenReturn(provisionerActionsApi);

        // when
        provisionService.notifyProvisioningStatusUpdate(projectKey, status, clientRequest, accessToken);

        // then
        verify(provisionerActionsApi).notifyProvisioningStatusUpdate(projectKey, status.name(), clientRequest);
    }

    @Test
    void givenAProjectKeyAndComponentId_whenDeleteProvisioningStatus_thenProvisioningBasicAuthApiIsCalled() {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";

        var provisionDeleteRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        // when
        provisionService.deleteProvisioningStatus(projectKey, componentId);

        // then
        var expectedRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        verify(provisionerActionsApi).deleteProvisioningStatus(projectKey, expectedRequest);
    }
}
