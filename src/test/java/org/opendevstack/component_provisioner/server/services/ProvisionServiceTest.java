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
import org.opendevstack.component_provisioner.server.mappers.ProvisioningStatusUpdateRequestParametersInnerMapper;
import org.opendevstack.component_provisioner.server.model.ProjectComponentExtendedInfoMother;
import org.opendevstack.component_provisioner.server.services.exceptions.InvalidIdException;

import java.net.URL;
import java.util.List;

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
    private ProvisioningStatusUpdateRequestParametersInnerMapper provisioningStatusUpdateRequestParametersInnerMapper;

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
                .build();

        verify(provisionerActionsApi).notifyProvisioningStatusUpdatePartially(projectKey, status.name(), expectedRequest);
    }

    @Test
    void givenInvalidIdInProjectComponent_whenDeleteProvisioningStatusIsCalled_thenThrowsRuntimeException() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var projectComponent = new ProjectComponentExtendedInfo();
        projectComponent.setCatalogItemId("!!!"); // Invalid base64

        // when / then
        assertThatThrownBy(() -> provisionService.deleteProvisioningStatus(projectKey, componentId, projectComponent, accessToken))
                .isInstanceOf(RuntimeException.class)
                .hasCauseInstanceOf(InvalidIdException.class);
    }

    @Test
    void givenAProjectKeyAndComponentIdAndAccessToken_whenDeleteProvisioningStatusIsCalled_thenInvokesProvisionerActionsApi() throws Exception {
        // given
        var projectKey = "PRJ";
        var componentId = "CID";
        var accessToken = "token";
        var baseUrl = "http://catalog.example.com";
        var projectComponent = ProjectComponentExtendedInfoMother.valid();

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

        when(apiClientsBuilder.provisionerActionsApi(accessToken, baseUrl)).thenReturn(provisionerActionsApi);
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
