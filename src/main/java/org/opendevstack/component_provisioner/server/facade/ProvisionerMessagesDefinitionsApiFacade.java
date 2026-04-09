package org.opendevstack.component_provisioner.server.facade;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionMessageDefinition;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionerMessageDefinition;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class ProvisionerMessagesDefinitionsApiFacade {

    private final EntitiesMapper entitiesMapper;

    public ResponseEntity<ProvisionerMessageDefinition> getMessageDefinition(Supplier<Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>>> supplier) {
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
