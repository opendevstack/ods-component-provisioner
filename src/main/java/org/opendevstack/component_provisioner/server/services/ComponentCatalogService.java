package org.opendevstack.component_provisioner.server.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemUserActionMessageDefinitionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProjectComponentsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.auth.HttpBearerAuth;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionMessageDefinition;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequestParametersInner;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.services.exceptions.CatalogClientException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static java.lang.String.format;

@Service
@Slf4j
public class ComponentCatalogService {

    @Qualifier("itemUserActionMessagesDefinitionsApi")
    private final CatalogItemUserActionMessageDefinitionsApi itemUserActionMessagesDefinitionsApi;

    @Qualifier("componentCatalogApiClient")
    private final ApiClient componentCatalogApiClient;

    @Qualifier("projectComponentsApi")
    private final ProjectComponentsApi projectComponentsApi;

    private final ApiClientsBuilder apiClientsBuilder;

    private final ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;

    private final ApplicationPropertiesConfiguration.ComponentProvisionerParametersProps parametersProps;

    public ComponentCatalogService(
            CatalogItemUserActionMessageDefinitionsApi itemUserActionMessagesDefinitionsApi,
            ApiClient componentCatalogApiClient,
            ProjectComponentsApi projectComponentsApi, ApiClientsBuilder apiClientsBuilder,
            ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps,
            @Qualifier("componentProvisionerParametersConfig") ApplicationPropertiesConfiguration.ComponentProvisionerParametersProps parametersProps
    ) {
        this.itemUserActionMessagesDefinitionsApi = itemUserActionMessagesDefinitionsApi;
        this.componentCatalogApiClient = componentCatalogApiClient;
        this.projectComponentsApi = projectComponentsApi;
        this.apiClientsBuilder = apiClientsBuilder;
        this.componentCatalogServiceProps = componentCatalogServiceProps;
        this.parametersProps = parametersProps;
    }

    public Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>> getCatalogItemUserActionMessageDefinition(
            String catalogItemId,
            String userActionId,
            String messageDefinitionId,
            Map<String, String> placeholdersValues) {
        log.debug("getCatalogItemUserActionMessageDefinition with catalogItemId {}, userActionId {}, messageDefinitionId {} and placeholdersValues {}", catalogItemId, userActionId, messageDefinitionId, placeholdersValues);

        return getMessageDefinition(
                () -> itemUserActionMessagesDefinitionsApi.getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(
                        catalogItemId,
                        userActionId,
                        messageDefinitionId,
                        placeholdersValues),
                format("""
                        Http exception requesting message definitions to Component Catalog with:\s
                        userActionId: '%s',\s
                        catalogItemId: '%s',\s
                        messageDefinitionId: '%s',\s
                        placeholdersValues: '%s',\s
                        status code: '%%s'
                       \s""",
                        userActionId,
                        catalogItemId,
                        messageDefinitionId,
                        Arrays.toString(placeholdersValues.entrySet().toArray())),
                format("""
                        REST Client exception requesting message definitions to Component Catalog with:
                        userActionId: '%s',
                        catalogItemId: '%s',
                        messageDefinitionId: '%s',
                        placeholdersValues: '%s'
                        """,
                        userActionId,
                        catalogItemId,
                        messageDefinitionId,
                        Arrays.toString(placeholdersValues.entrySet().toArray()))
        );

    }

    public void notifyComponentCatalogProvisionStarts(String projectKey,
                                                      String componentId,
                                                      String catalogItemId,
                                                      String componentUrl,
                                                      String idToken,
                                                      String accessToken,
                                                      Map<String, List<String>> parameters) {
        log.debug("Notifying component catalog about starting provision for project {}, componentId: {}, catalogItemId: {}, componentUrl: {}",
                projectKey, componentId, catalogItemId, componentUrl);

        var obfuscatedParameters = obfuscateParameters(parameters).entrySet().stream()
                .map(e -> ProvisioningStatusUpdateRequestParametersInner.builder()
                        .name(e.getKey())
                        .values(e.getValue())
                        .build())
                .toList();

        var provisioningStatusUpdateRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .accessToken(accessToken)
                .parameters(obfuscatedParameters)
                .build();

        var apiClient = apiClientsBuilder.componentCatalogApiClient(idToken, componentCatalogServiceProps.getBaseRestUrl().toString());
        var provisionerActionsApi = apiClientsBuilder.provisionerActionsApi(apiClient);

        log.debug("Calling provisionerActionsApi.notifyProvisioningStatusUpdate. ProjectKey: {}, status: {}, notifyProvisioningCompletedRequest: {}",
                projectKey, "CREATING", provisioningStatusUpdateRequest);

        provisionerActionsApi.notifyProvisioningStatusUpdate(projectKey, "CREATING", provisioningStatusUpdateRequest);
    }

    public List<ProjectComponentInfo> getProjectComponents(String projectKey, String idToken, String accessToken) {
        var auth = (HttpBearerAuth) componentCatalogApiClient.getAuthentication("bearerAuth");
        auth.setBearerToken(idToken);

        return projectComponentsApi.getProjectComponents(projectKey, accessToken);
    }

    public CatalogItem getCatalogItem(String idToken, String accessToken, String catalogItemId, String projectKey) {
        var apiClient = apiClientsBuilder.componentCatalogApiClient(idToken, componentCatalogServiceProps.getBaseRestUrl().toString());
        var catalogItemsApi = apiClientsBuilder.catalogItemsApi(apiClient);

        var catalogItem = catalogItemsApi.getCatalogItemByIdForProjectKey(catalogItemId, projectKey, accessToken);

        log.debug("Retrieved catalog item with id {} for project key {}: {}", catalogItemId, projectKey, catalogItem);

        return catalogItem;
    }

    private Map<String, List<String>> obfuscateParameters(Map<String, List<String>> parameters) {
        if (parameters == null) {
            return Collections.emptyMap();
        }

        var blacklist = parametersProps.getBlacklist();
        if (blacklist == null || blacklist.length == 0) {
            return parameters;
        }

        List<String> blacklistedKeys = Arrays.asList(blacklist);
        Map<String, List<String>> obfuscatedParameters = new java.util.HashMap<>();

        for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
            if (blacklistedKeys.contains(entry.getKey())) {
                obfuscatedParameters.put(entry.getKey(), List.of("<PRIVATE>"));
            } else {
                obfuscatedParameters.put(entry.getKey(), entry.getValue());
            }
        }

        return obfuscatedParameters;
    }

    private Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>> getMessageDefinition(Supplier<ResponseEntity<CatalogItemUserActionMessageDefinition>> supplier, String httpStatusCodeErrorMessage, String restClientErrorMessage) {
        try {
            ResponseEntity<CatalogItemUserActionMessageDefinition> response = supplier.get();

            Optional<CatalogItemUserActionMessageDefinition> result = Optional.ofNullable(response.getBody());

            return Pair.of(
                    response.getStatusCode(),
                    result
            );
        } catch (HttpStatusCodeException e) {

            log.error(httpStatusCodeErrorMessage, e);

            return Pair.of(e.getStatusCode(), Optional.empty());
        } catch (RestClientException e) {
            log.error(restClientErrorMessage, e);

            throw new CatalogClientException(e);
        }
    }
}
