package com.boehringer.componentprovisioner.server.services;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.api.CatalogItemUserActionMessageDefinitionsApi;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.CatalogItemUserActionMessageDefinition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComponentCatalogServiceTest {

    @Mock
    private CatalogItemUserActionMessageDefinitionsApi itemUserActionMessagesDefinitionsApi;

    @InjectMocks
    private ComponentCatalogService componentCatalogService;

    @Test
    void getCatalogItemUserActionMessageDefinition_returnsHttpStatusAndMessageDefinition_whenApiCallIsSuccessful() {
        var userActionId = "user-action-id";
        var messageDefinitionId = "message-definition-id";
        var placeholdersValues = Map.of("key", "value");

        var apiResponse = new ResponseEntity<>(new CatalogItemUserActionMessageDefinition(), HttpStatus.OK);

        when(itemUserActionMessagesDefinitionsApi.getMessageDefinitionByIdWithHttpInfo(
                userActionId,
                messageDefinitionId,
                placeholdersValues)
        ).thenReturn(apiResponse);

        var result = componentCatalogService.getCatalogItemUserActionMessageDefinition(
                userActionId,
                messageDefinitionId,
                placeholdersValues);

        assertEquals(HttpStatus.OK, result.getLeft());
        assertTrue(result.getRight().isPresent());
        assertEquals(apiResponse.getBody(), result.getRight().get());
    }

    @Test
    void getCatalogItemUserActionMessageDefinition_returnsHttpStatusAndEmptyOptional_whenApiResponseBodyIsNull() {
        var userActionId = "user-action-id";
        var messageDefinitionId = "message-definition-id";
        var placeholdersValues = Map.of("key", "value");

        ResponseEntity<CatalogItemUserActionMessageDefinition> apiResponse = new ResponseEntity<>(null, HttpStatus.OK);

        when(itemUserActionMessagesDefinitionsApi.getMessageDefinitionByIdWithHttpInfo(
                userActionId,
                messageDefinitionId,
                placeholdersValues)
        ).thenReturn(apiResponse);

        var result = componentCatalogService.getCatalogItemUserActionMessageDefinition(
                userActionId,
                messageDefinitionId,
                placeholdersValues);

        assertEquals(HttpStatus.OK, result.getLeft());
        assertTrue(result.getRight().isEmpty());
    }

    @Test
    void getCatalogItemUserActionMessageDefinition_returnsErrorStatusAndEmptyOptional_whenHttpStatusCodeExceptionOccurs() {
        var userActionId = "user-action-id";
        var messageDefinitionId = "message-definition-id";
        var placeholdersValues = Map.of("key", "value");

        var exception = HttpClientErrorException.BadRequest.create(
                HttpStatus.BAD_REQUEST,
                null,
                null,
                null,
                null);

        when(itemUserActionMessagesDefinitionsApi.getMessageDefinitionByIdWithHttpInfo(
                userActionId,
                messageDefinitionId,
                placeholdersValues)
        ).thenThrow(exception);

        var result = componentCatalogService.getCatalogItemUserActionMessageDefinition(
                userActionId,
                messageDefinitionId,
                placeholdersValues);

        assertEquals(HttpStatus.BAD_REQUEST, result.getLeft());
        assertTrue(result.getRight().isEmpty());
    }

    @Test
    void getCatalogItemUserActionMessageDefinition_throwsRuntimeException_whenRestClientExceptionOccurs() {
        var userActionId = "user-action-id";
        var messageDefinitionId = "message-definition-id";
        var placeholdersValues = Map.of("key", "value");

        var exception = new RestClientException("Error");

        when(itemUserActionMessagesDefinitionsApi.getMessageDefinitionByIdWithHttpInfo(
                userActionId,
                messageDefinitionId,
                placeholdersValues)
        ).thenThrow(exception);

        assertThrows(
                RuntimeException.class,
                () -> componentCatalogService.getCatalogItemUserActionMessageDefinition(
                        userActionId,
                        messageDefinitionId,
                        placeholdersValues)
        );
    }
}
