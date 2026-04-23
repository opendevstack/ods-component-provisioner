package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.*;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.mappers.ProvisioningStatusUpdateRequestParametersInnerMapper;
import org.opendevstack.component_provisioner.server.services.exceptions.InvalidIdException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.opendevstack.component_provisioner.server.services.common.IdEncoderDecoder.idDecode;
import static org.opendevstack.component_provisioner.server.services.common.IdEncoderDecoder.idEncode;

@Service
@Slf4j
@AllArgsConstructor
public class ProvisionService {

    private final ApiClientsBuilder apiClientsBuilder;
    private final ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;
    private final ProvisioningStatusUpdateRequestParametersInnerMapper provisioningStatusUpdateRequestParametersInnerMapper;

    public void notifyProvisioningStatusUpdate(String projectKey, ProjectComponentStatus status, String componentId,
                                               String catalogItemId, String componentUrl, String accessToken) {
        log.info("Notifying provisioning completed");

        var provisionerActionsApi = apiClientsBuilder.provisionerActionsApi(accessToken, componentCatalogServiceProps.getBaseRestUrl().toString());

        var notifyProvisioningCompletedRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .build();

        log.debug("Calling provisionerActionsApi.notifyProvisioningStatusUpdatePartially. ProjectKey: {}, status: {}, notifyProvisioningCompletedRequest: {}",
                projectKey, status.name(), notifyProvisioningCompletedRequest);

        provisionerActionsApi.notifyProvisioningStatusUpdatePartially(projectKey, status.name(), notifyProvisioningCompletedRequest);
    }

    public void deleteProvisioningStatus(String projectKey,
                                         String componentId,
                                         ProjectComponentExtendedInfo projectComponent,
                                         String accessToken) {
        log.info("Deleting provisioning completed. Project Key: {}, componentId: {}", projectKey, componentId);

        var catalogItemId = composeCatalogItemId(projectComponent);

        var apiClient = apiClientsBuilder.componentCatalogApiClient(accessToken, componentCatalogServiceProps.getBaseRestUrl().toString());

        var catalogItemsApi = apiClientsBuilder.catalogItemsApi(apiClient);
        var catalogItem = catalogItemsApi.getCatalogItemById(catalogItemId);

        var parametersToSend = extractDeletionParameters(catalogItem, projectComponent);

        if (parametersToSend.isEmpty()) {
            log.debug("No parameters marked as sendOnDeletion found");
        }

        var provisioningDeleteRequest = ProvisioningDeleteRequest.builder()
                .componentId(componentId)
                .parameters(parametersToSend)
                .build();

        var provisionerActionsApi = apiClientsBuilder.provisionerActionsApi(accessToken,
                componentCatalogServiceProps.getBaseRestUrl().toString());

        provisionerActionsApi.deleteProvisioningStatus(projectKey, provisioningDeleteRequest);
    }

    private String composeCatalogItemId(ProjectComponentExtendedInfo projectComponents) {
        try {
            var decodedCatalogItemId = idDecode(projectComponents.getCatalogItemId());
            var decodedCatalogItemRef = idDecode(projectComponents.getCatalogItemRef());
            return idEncode(Strings.concat(decodedCatalogItemId, decodedCatalogItemRef));

        } catch (InvalidIdException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ProvisioningStatusUpdateRequestParametersInner> extractDeletionParameters(
            CatalogItem catalogItem,
            ProjectComponentExtendedInfo projectComponent) {
        var projectParametersByName =
                Optional.ofNullable(projectComponent.getParameters())
                        .orElse(List.of())
                        .stream()
                        .filter(p -> p.getName() != null)
                        .collect(Collectors.toMap(
                                ProjectComponentParameter::getName,
                                Function.identity(), (a, b) -> a
                        ));

        return Optional.ofNullable(catalogItem.getUserActions())
                .orElse(List.of())
                .stream()
                .peek(action -> log.debug("User action found: {}", action))
                .flatMap(action ->
                        Optional.ofNullable(action.getParameters())
                                .orElse(List.of())
                                .stream()
                )
                .peek(param -> log.debug("Parameter found: {}", param))
                .filter(param -> Boolean.TRUE.equals(param.getSendOnDeletion()))
                .map(param -> projectParametersByName.get(param.getName()))
                .filter(Objects::nonNull)
                .map(provisioningStatusUpdateRequestParametersInnerMapper::toTarget)
                .toList();
    }
}
