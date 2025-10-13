package com.boehringer.componentprovisioner.server.controllers;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.CatalogItemUserActionMessageDefinition;
import com.boehringer.componentprovisioner.server.mappers.EntitiesMapper;
import com.boehringer.componentprovisioner.server.model.ProvisionerMessageDefinition;
import com.boehringer.componentprovisioner.server.services.ComponentCatalogService;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerMessagesDefinitionsApiControllerTest {

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private EntitiesMapper entitiesMapper;

    @InjectMocks
    private ProvisionerMessagesDefinitionsApiController controller;

    @Test
    void getMessageDefinitionById_returnsResponseEntityWithMappedMessageDefinition_whenServiceReturnsSuccess() {
        var id = "message-id";
        var placeholdersValues = Map.of("key", "value");

        Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>> catalogResponse =
                Pair.of(HttpStatus.OK, Optional.of(new CatalogItemUserActionMessageDefinition()));

        var provisionerMessageDefinition = new ProvisionerMessageDefinition();

        when(componentCatalogService.getCatalogItemUserActionMessageDefinition(
                "PROVISION", id, placeholdersValues))
                .thenReturn(catalogResponse);

        when(entitiesMapper.asProvisionerMessageDefinition(any(CatalogItemUserActionMessageDefinition.class)))
                .thenReturn(provisionerMessageDefinition);

        var response = controller.getMessageDefinitionById(id, placeholdersValues);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(provisionerMessageDefinition, response.getBody());
    }

    @Test
    void getMessageDefinitionById_returnsResponseEntityWithNullBody_whenServiceReturnsEmptyResponse() {
        var id = "message-id";
        var placeholdersValues = Map.of("key", "value");

        Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>> catalogResponse =
                Pair.of(HttpStatus.NO_CONTENT, Optional.empty());

        when(componentCatalogService.getCatalogItemUserActionMessageDefinition(
                "PROVISION", id, placeholdersValues))
                .thenReturn(catalogResponse);

        var response = controller.getMessageDefinitionById(id, placeholdersValues);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getMessageDefinitionById_returnsErrorStatus_whenServiceThrowsHttpStatusCodeException() {
        var id = "message-id";
        var placeholdersValues = Map.of("key", "value");

        var exception = HttpClientErrorException.BadRequest.create(
                HttpStatus.BAD_REQUEST,
                null,
                null,
                null,
                null);

        when(componentCatalogService.getCatalogItemUserActionMessageDefinition(
                "PROVISION", id, placeholdersValues))
                .thenThrow(exception);

        assertThrows(HttpStatusCodeException.class, () -> controller.getMessageDefinitionById(id, placeholdersValues));
    }
}