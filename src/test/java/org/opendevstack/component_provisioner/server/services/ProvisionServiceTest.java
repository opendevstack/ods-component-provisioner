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
    void givenAProjectKeyAndStatusAndComponentIdAndCatalogItemIdAndComponentUrlAndAccessToken_whenNotifyProvisioningStatusUpdateIsCalled_thenInvokesProvisionerActionsApi() throws Exception {
        // given
        var projectKey = "PRJ";
        var status = ProjectComponentStatus.CREATED;
        var componentId = "CID";
        var catalogItemId = "CAT";
        var componentUrl = "http://example.com";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(new URL(baseUrl));
        when(apiClientsBuilder.provisionerActionsApi(accessToken, baseUrl)).thenReturn(provisionerActionsApi);

        // when
        provisionService.notifyProvisioningStatusUpdate(projectKey, status, componentId, catalogItemId, componentUrl, accessToken);

        // then
        var expectedRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .accessToken(accessToken)
                .build();

        verify(provisionerActionsApi).notifyProvisioningStatusUpdatePartially(projectKey, status.name(), expectedRequest);
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
