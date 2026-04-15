package org.opendevstack.component_provisioner.server.services;

import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;

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
    private org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient apiClient;

    @InjectMocks
    private ProvisionService provisionService;

    @Test
    void givenAProvisionClient_WhenNotifyProvisioningCompleted_ThenProvisioningIsNotified() throws java.net.MalformedURLException {
        // given
        var projectKey = "projectKey";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";
        var idToken = "idToken";
        var accessToken = "accessToken";
        var baseUrl = "http://localhost";

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(java.net.URI.create(baseUrl).toURL());
        when(apiClientsBuilder.provisionerActionsApi(eq(idToken), eq(baseUrl))).thenReturn(provisionerActionsApi);

        // when
        provisionService.notifyProvisioningStatusUpdate(projectKey, status, componentId, catalogItemId, componentUrl, accessToken);

        // then
        verify(provisionerActionsApi).notifyProvisioningStatusUpdatePartially(projectKey, status.name(), ProvisioningStatusUpdateRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .accessToken(accessToken)
                .build());
    }

    @Test
    void givenAProjectKey_andAComponentId_whenDeleteProvisioningStatus_thenProvisioningApiIsCalled() throws java.net.MalformedURLException {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";
        var idToken = "idToken";
        var baseUrl = "http://localhost";

        var provisionDeleteRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(java.net.URI.create(baseUrl).toURL());
        when(apiClientsBuilder.provisionerActionsApi(eq(idToken), eq(baseUrl))).thenReturn(provisionerActionsApi);

        // when
        provisionService.deleteProvisioningStatus(projectKey, componentId, idToken);

        // then
        verify(provisionerActionsApi).deleteProvisioningStatus(projectKey, provisionDeleteRequest);
    }
}
