package com.boehringer.componentprovisioner.server.services;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.api.CatalogItemUserActionMessageDefinitionsApi;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.CatalogItemUserActionMessageDefinition;
import com.boehringer.componentprovisioner.server.services.exceptions.CatalogClientException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static java.lang.String.format;

@Service
@AllArgsConstructor
@Slf4j
public class ComponentCatalogService {

    @Qualifier("itemUserActionMessagesDefinitionsApi")
    private final CatalogItemUserActionMessageDefinitionsApi itemUserActionMessagesDefinitionsApi;

    public Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>> getCatalogItemUserActionMessageDefinition(
            String userActionId,
            String messageDefinitionId,
            Map<String, String> placeholdersValues) {
        log.debug("getCatalogItemUserActionMessageDefinition with userActionId {}, messageDefinitionId {} and placeholdersValues {}", userActionId, messageDefinitionId, placeholdersValues);

        return getMessageDefinition(
                () -> itemUserActionMessagesDefinitionsApi.getMessageDefinitionByIdWithHttpInfo(
                        userActionId,
                        messageDefinitionId,
                        placeholdersValues),
                format("""
                        Http exception requesting message definitions to Component Catalog with:\s
                        userActionId: '%s',\s
                        messageDefinitionId: '%s',\s
                        placeholdersValues: '%s',\s
                        status code: '%%s'
                       \s""",
                        userActionId,
                        messageDefinitionId,
                        Arrays.toString(placeholdersValues.entrySet().toArray())),
                format("""
                        REST Client exception requesting message definitions to Component Catalog with:
                        userActionId: '%s',
                        messageDefinitionId: '%s',
                        placeholdersValues: '%s'
                        """,
                        userActionId,
                        messageDefinitionId,
                        Arrays.toString(placeholdersValues.entrySet().toArray()))
        );

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
