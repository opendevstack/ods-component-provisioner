package org.opendevstack.component_provisioner.server.services;

import java.net.MalformedURLException;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatus;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.mappers.CreateIncidentParameterMapper;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerServiceTest {

    @Mock
    private ApiClientsBuilder apiClientsBuilder;

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;

    @Mock
    private CreateIncidentParameterMapper createIncidentParameterMapper;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private ProvisionerActionsApi provisionerActionsApi;

    @Mock
    private EntitiesMapper entitiesMapper;

    @InjectMocks
    private ProvisionService provisionService;

    @Test
    void givenProjectKeyAndStatus_whenNotifyStatusUpdate_thenCallsProvisionerActionsApi() throws MalformedURLException {
        // given
        var projectKey = "projectKey";
        var status =
                org.opendevstack.component_provisioner.server.model.ProvisioningStatus.CREATED;
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

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(URI.create(baseUrl).toURL());
        when(apiClientsBuilder.provisionerActionsApi(eq(accessToken), eq(baseUrl))).thenReturn(provisionerActionsApi);
        when(entitiesMapper.asProvisioningStatus(status)).thenReturn(ProvisioningStatus.CREATED);

        // when
        provisionService.notifyProvisioningStatusUpdate(projectKey, status, clientRequest, accessToken);

        // then
        verify(provisionerActionsApi).notifyProvisioningStatusUpdate(projectKey, ProvisioningStatus.CREATED, clientRequest);
    }

    @Test
    void givenAProjectKeyAndComponentId_whenDeleteProvisioningStatus_thenProvisioningBasicAuthApiIsCalled() {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";

        // when
        provisionService.deleteProvisioningStatus(projectKey, componentId);

        // then
        var expectedRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        verify(provisionerActionsApi).deleteProvisioningStatus(projectKey, expectedRequest);
    }
}
