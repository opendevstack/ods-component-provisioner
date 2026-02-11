package org.opendevstack.component_provisioner.server.controllers;

import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionMessageDefinition;
import org.opendevstack.component_provisioner.server.api.ProvisionerMessagesDefinitionsApi;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionerMessageDefinition;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
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

    private ComponentCatalogService componentCatalogService;
    private EntitiesMapper entitiesMapper;

    @Override
    public ResponseEntity<ProvisionerMessageDefinition> getMessageDefinitionByCatalogItemIdAndMessageId(String catalogItemId,
                                                                                                        String action,
                                                                                                        String id,
                                                                                                        Map<String, String> placeholdersValues) {
        return getMessageDefinition(
                () -> componentCatalogService.getCatalogItemUserActionMessageDefinition(
                        catalogItemId, action, id, placeholdersValues)
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
