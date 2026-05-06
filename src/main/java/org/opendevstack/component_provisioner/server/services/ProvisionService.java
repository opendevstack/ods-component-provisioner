package org.opendevstack.component_provisioner.server.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.jspecify.annotations.NonNull;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.*;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.model.ActionType;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.mappers.CreateIncidentParameterMapper;
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

    private static final String DELETION_WORKFLOW = "deletion_workflow";

    private final ApiClientsBuilder apiClientsBuilder;
    private final ComponentCatalogService componentCatalogService;
    private final ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;
    private final CreateIncidentParameterMapper createIncidentParameterMapper;
    private final AuthenticationProvider authenticationProvider;
    private final ProvisionerActionsApi provisionerActionsBasicAuthApi;

    public ProvisionService(ApiClientsBuilder apiClientsBuilder, ComponentCatalogService componentCatalogService,
                            ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps, CreateIncidentParameterMapper createIncidentParameterMapper, AuthenticationProvider authenticationProvider,
                            @Qualifier("provisionerActionsBasicAuthApi") ProvisionerActionsApi provisionerActionsBasicAuthApi) {
        this.apiClientsBuilder = apiClientsBuilder;
        this.componentCatalogService = componentCatalogService;
        this.componentCatalogServiceProps = componentCatalogServiceProps;
        this.createIncidentParameterMapper = createIncidentParameterMapper;
        this.authenticationProvider = authenticationProvider;
        this.provisionerActionsBasicAuthApi = provisionerActionsBasicAuthApi;
    }

    public void notifyProvisioningStatusUpdate(String projectKey,
                                               ProjectComponentStatus status,
                                               ProvisioningStatusUpdateRequest clientRequest,
                                               String accessToken) {
        log.debug("PUT component-catalog /provision/{}/{} body={}", projectKey, status.name(), clientRequest);

        catalogProvisionerActionsApi(accessToken)
                .notifyProvisioningStatusUpdate(projectKey, status.name(), clientRequest);
    }

    public void notifyProvisioningStatusUpdatePartially(String projectKey,
                                                        ProjectComponentStatus status,
                                                        ProvisioningStatusUpdateRequest clientRequest,
                                                        String accessToken) {
        log.debug("PATCH component-catalog /provision/{}/{} body={}", projectKey, status.name(), clientRequest);

        catalogProvisionerActionsApi(accessToken)
                .notifyProvisioningStatusUpdatePartially(projectKey, status.name(), clientRequest);
    }

    public void deleteProvisioningStatus(String projectKey,
                                         String componentId) {
        log.info("Deleting provisioning status. Project Key: {}, componentId: {}", projectKey, componentId);

        var deleteRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .build();

        catalogProvisionerActionsBasicAuthApi()
                .deleteProvisioningStatus(projectKey, deleteRequest);
    }

    private ProvisionerActionsApi catalogProvisionerActionsApi(String accessToken) {
        return apiClientsBuilder.provisionerActionsApi(accessToken,
                componentCatalogServiceProps.getBaseRestUrl().toString());
    }

    public List<CreateIncidentParameter> getDeletionParameters(String projectKey, String componentId) {
        var projectComponent = componentCatalogService.getProjectComponentExtendedInfo(projectKey, componentId);

        var catalogItemId = composeCatalogItemId(projectComponent);

        var apiClient = apiClientsBuilder.componentCatalogApiClient(authenticationProvider.getAccessToken(), componentCatalogServiceProps.getBaseRestUrl().toString());
        var catalogItemsApi = apiClientsBuilder.catalogItemsApi(apiClient);
        var catalogItem = catalogItemsApi.getCatalogItemById(catalogItemId);

        return extractDeletionParameters(catalogItem, projectComponent, ActionType.PROVISION.getValue());
    }

    public String getDeletionWorkflow(String projectKey, String componentId) {
        var projectComponent = componentCatalogService.getProjectComponentExtendedInfo(projectKey, componentId);
        var parameterMap = getProjectComponentParameterMap(projectComponent);

        if (parameterMap.containsKey(DELETION_WORKFLOW)) {
            var deletionWorkflow = parameterMap.get(DELETION_WORKFLOW);
            assert deletionWorkflow.getValues() != null;
            return deletionWorkflow.getValues().getFirst();
        }

        return "";
    }

    @SneakyThrows
    private String composeCatalogItemId(ProjectComponentExtendedInfo projectComponents) {
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
                    var componentValue = projectParametersByName.get(param.getName());
                    if (componentValue == null) return null;
                    return createIncidentParameterMapper.toTarget(param, componentValue);
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
