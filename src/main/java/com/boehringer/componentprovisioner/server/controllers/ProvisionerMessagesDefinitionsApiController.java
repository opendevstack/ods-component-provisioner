package com.boehringer.componentprovisioner.server.controllers;

import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.CatalogItemUserActionMessageDefinition;
import com.boehringer.componentprovisioner.server.api.ProvisionerMessagesDefinitionsApi;
import com.boehringer.componentprovisioner.server.mappers.EntitiesMapper;
import com.boehringer.componentprovisioner.server.model.ProvisionerMessageDefinition;
import com.boehringer.componentprovisioner.server.services.ComponentCatalogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProvisionerMessagesDefinitionsApiController implements ProvisionerMessagesDefinitionsApi {

    private static final String PROVISION_ACTION_ID = "PROVISION";

    private ComponentCatalogService componentCatalogService;
    private EntitiesMapper entitiesMapper;

    @Override
    public ResponseEntity<ProvisionerMessageDefinition> getMessageDefinitionById(String id,
                                                                                 Map<String, String> placeholdersValues) {
        return getMessageDefinition(
                () -> componentCatalogService.getCatalogItemUserActionMessageDefinition(
                        PROVISION_ACTION_ID, id, placeholdersValues)
        );
    }

    @Override
    public ResponseEntity<ProvisionerMessageDefinition> getMessageDefinitionByCatalogItemIdAndMessageId(String catalogItemId, String id, Map<String, String> placeholdersValues) {
        return getMessageDefinition(
                () -> componentCatalogService.getCatalogItemUserActionMessageDefinition(
                        catalogItemId, PROVISION_ACTION_ID, id, placeholdersValues)
        );
    }

    private ResponseEntity<ProvisionerMessageDefinition> getMessageDefinition(Supplier<Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>>> supplier) {
        var result = supplier.get();

        var componentCatalogHttpStatus = result.getLeft();
        var provisionerMessageDefinition = result.getRight()
                .map(entitiesMapper::asProvisionerMessageDefinition)
                .orElse(null);

        return ResponseEntity
                .status(componentCatalogHttpStatus)
                .body(provisionerMessageDefinition);
    }
}
