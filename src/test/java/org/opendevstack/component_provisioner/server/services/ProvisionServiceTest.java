package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;

import java.net.URL;

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
