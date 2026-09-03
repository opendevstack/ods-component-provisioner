package org.opendevstack.component_provisioner.server.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.jspecify.annotations.NonNull;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.*;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.model.ActionType;
import org.opendevstack.component_provisioner.server.mappers.CreateIncidentParameterMapper;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.opendevstack.component_provisioner.server.services.common.IdEncoderDecoder.idDecode;
import static org.opendevstack.component_provisioner.server.services.common.IdEncoderDecoder.idEncode;

@Service
@Slf4j
public class ProvisionService {

    private final ApiClientsBuilder apiClientsBuilder;
    private final ComponentCatalogService componentCatalogService;
    private final ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;
    private final CreateIncidentParameterMapper createIncidentParameterMapper;
    private final AuthenticationProvider authenticationProvider;
    private final ProvisionerActionsApi provisionerActionsBasicAuthApi;
    private final EntitiesMapper entitiesMapper;

    public ProvisionService(ApiClientsBuilder apiClientsBuilder, ComponentCatalogService componentCatalogService,
                            ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps, CreateIncidentParameterMapper createIncidentParameterMapper, AuthenticationProvider authenticationProvider,
                            @Qualifier("provisionerActionsBasicAuthApi") ProvisionerActionsApi provisionerActionsBasicAuthApi, EntitiesMapper entitiesMapper) {
        this.apiClientsBuilder = apiClientsBuilder;
        this.componentCatalogService = componentCatalogService;
        this.componentCatalogServiceProps = componentCatalogServiceProps;
        this.createIncidentParameterMapper = createIncidentParameterMapper;
        this.authenticationProvider = authenticationProvider;
        this.provisionerActionsBasicAuthApi = provisionerActionsBasicAuthApi;
        this.entitiesMapper = entitiesMapper;
    }

    public void notifyProvisioningStatusUpdate(String projectKey,
                                               org.opendevstack.component_provisioner.server.model.ProvisioningStatus status,
                                               ProvisioningStatusUpdateRequest clientRequest,
                                               String accessToken) {
        log.debug("PUT component-catalog /provision/{}/{} body={}", projectKey, status.name(), clientRequest);

        catalogProvisionerActionsApi(accessToken)
                .notifyProvisioningStatusUpdate(projectKey, entitiesMapper.asProvisioningStatus(status), clientRequest);
    }

    public void notifyProvisioningStatusUpdatePartially(String projectKey,
                                                        org.opendevstack.component_provisioner.server.model.ProvisioningStatus status,
                                                        ProvisioningStatusUpdateRequest clientRequest,
                                                        String accessToken) {
        log.debug("PATCH component-catalog /provision/{}/{} body={}", projectKey, status.name(), clientRequest);

        catalogProvisionerActionsApi(accessToken)
                .notifyProvisioningStatusUpdatePartially(projectKey, entitiesMapper.asProvisioningStatus(status), clientRequest);
    }

    public void deleteProvisioningStatus(String projectKey,
                                         String componentId,
                                         List<org.opendevstack.component_provisioner.server.model.ProvisioningDeleteRequestParametersInner> parameters) {
        log.info("Deleting provisioning status. Project Key: {}, componentId: {}", projectKey, componentId);

        var deleteRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .parameters(parameters.stream()
                        .map(entitiesMapper::asProvisioningDeleteRequestParametersInner)
                        .toList())
                .build();

        catalogProvisionerActionsBasicAuthApi()
                .deleteProvisioningStatus(projectKey, deleteRequest);
    }

    private ProvisionerActionsApi catalogProvisionerActionsApi(String accessToken) {
        return apiClientsBuilder.provisionerActionsApi(accessToken,
                componentCatalogServiceProps.getBaseRestUrl().toString());
    }

    public List<CreateIncidentParameter> getDeletionParameters(String projectKey, String componentId) {
        var projectComponent = componentCatalogService.getProjectComponentById(authenticationProvider.getAccessToken(), projectKey, componentId);

        var catalogItemId = composeCatalogItemId(projectComponent);

        var apiClient = apiClientsBuilder.componentCatalogApiClient(authenticationProvider.getAccessToken(), componentCatalogServiceProps.getBaseRestUrl().toString());
        var catalogItemsApi = apiClientsBuilder.catalogItemsApi(apiClient);
        var catalogItem = catalogItemsApi.getCatalogItemById(catalogItemId, null);

        return extractDeletionParameters(catalogItem, projectComponent, ActionType.PROVISION.getValue());
    }

    @SneakyThrows
    public String composeCatalogItemId(ProjectComponentExtendedInfo projectComponents) {
        var decodedCatalogItemId = idDecode(projectComponents.getCatalogItemId());
        var decodedCatalogItemRef = idDecode(projectComponents.getCatalogItemRef());
        return idEncode(Strings.concat(decodedCatalogItemId, decodedCatalogItemRef));
    }

    private List<CreateIncidentParameter> extractDeletionParameters(
            CatalogItem catalogItem,
            ProjectComponentExtendedInfo projectComponent,
            String actionId) {
        var projectParametersByName = getProjectComponentParameterMap(projectComponent);

        return Optional.ofNullable(catalogItem.getUserActions())
                .orElse(List.of())
                .stream()
                .peek(action -> log.debug("User action found: {}", action))
                .filter(action -> actionId.equals(action.getId()))
                .flatMap(action ->
                        Optional.ofNullable(action.getParameters())
                                .orElse(List.of())
                                .stream()
                )
                .peek(param -> log.debug("Parameter found: {}", param))
                .filter(param -> Boolean.TRUE.equals(param.getSendOnDeletion()))
                .map(param -> {
                    var paramComponent = projectParametersByName.get(param.getName());
                    if (paramComponent == null) return null;
                    return createIncidentParameterMapper.toTarget(param, paramComponent.getValues());
                })
                .filter(Objects::nonNull)
                .toList();
    }

    public static @NonNull Map<String, ProjectComponentParameter> getProjectComponentParameterMap(ProjectComponentExtendedInfo projectComponent) {
        return Optional.ofNullable(projectComponent.getParameters())
                .orElse(List.of())
                .stream()
                .filter(p -> p.getName() != null)
                .collect(Collectors.toMap(
                        ProjectComponentParameter::getName,
                        Function.identity(), (a, b) -> a
                ));
    }

    private ProvisionerActionsApi catalogProvisionerActionsBasicAuthApi() {
        return provisionerActionsBasicAuthApi;
    }
}
