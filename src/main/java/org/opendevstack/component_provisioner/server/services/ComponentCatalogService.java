package org.opendevstack.component_provisioner.server.services;

import org.opendevstack.component_provisioner.client.component_catalog.v1.auth.HttpBearerAuth;
import org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemUserActionMessageDefinitionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProjectComponentsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionMessageDefinition;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequestParametersInner;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.services.exceptions.CatalogClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.*;
import java.util.function.Supplier;

import static java.lang.String.format;

@Service
@Slf4j
public class ComponentCatalogService {

    @Qualifier("itemUserActionMessagesDefinitionsApi")
    private final CatalogItemUserActionMessageDefinitionsApi itemUserActionMessagesDefinitionsApi;

    @Qualifier("provisionerActionsApi")
    private final ProvisionerActionsApi provisionerActionsApi;

    @Qualifier("componentCatalogApiClient")
    private final ApiClient componentCatalogApiClient;

    @Qualifier("projectComponentsApi")
    private final ProjectComponentsApi projectComponentsApi;

    private final ApplicationPropertiesConfiguration.ComponentProvisionerParametersProps parametersProps;

    public ComponentCatalogService(
            CatalogItemUserActionMessageDefinitionsApi itemUserActionMessagesDefinitionsApi,
            ProvisionerActionsApi provisionerActionsApi, ApiClient componentCatalogApiClient,
            ProjectComponentsApi projectComponentsApi,
            @Qualifier("componentProvisionerParametersConfig") ApplicationPropertiesConfiguration.ComponentProvisionerParametersProps parametersProps
    ) {
        this.itemUserActionMessagesDefinitionsApi = itemUserActionMessagesDefinitionsApi;
        this.provisionerActionsApi = provisionerActionsApi;
        this.componentCatalogApiClient = componentCatalogApiClient;
        this.projectComponentsApi = projectComponentsApi;
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
                                                      Map<String, List<String>> parameters) {
        var obfuscatedParameters = obfuscateParameters(parameters).entrySet().stream()
                .map(e -> ProvisioningStatusUpdateRequestParametersInner.builder()
                        .name(e.getKey())
                        .value(e.getValue())
                        .build())
                .toList();

        var provisioningStatusUpdateRequest = ProvisioningStatusUpdateRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .parameters(obfuscatedParameters)
                .build();

        provisionerActionsApi.notifyProvisioningStatusUpdate(projectKey, "CREATING", provisioningStatusUpdateRequest);
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

    public List<ProjectComponentInfo> getProjectComponents(String projectKey, String idToken, String accessToken) {
        var auth = (HttpBearerAuth) componentCatalogApiClient.getAuthentication("bearerAuth");
        auth.setBearerToken(idToken);

        return projectComponentsApi.getProjectComponents(projectKey, accessToken);
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
