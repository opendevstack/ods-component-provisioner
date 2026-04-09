package org.opendevstack.component_provisioner.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.api.ProvisionerMessagesDefinitionsApi;
import org.opendevstack.component_provisioner.server.facade.ProvisionerMessagesDefinitionsApiFacade;
import org.opendevstack.component_provisioner.server.model.ProvisionerMessageDefinition;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProvisionerMessagesDefinitionsApiController implements ProvisionerMessagesDefinitionsApi {

    private final ComponentCatalogService componentCatalogService;
    private final ProvisionerMessagesDefinitionsApiFacade provisionerMessagesDefinitionsApiFacade;

    @Override
    public ResponseEntity<ProvisionerMessageDefinition> getMessageDefinitionByCatalogItemIdAndMessageId(String catalogItemId,
                                                                                                        String action,
                                                                                                        String id,
                                                                                                        Map<String, String> placeholdersValues) {
        return provisionerMessagesDefinitionsApiFacade.getMessageDefinition(
                () -> componentCatalogService.getCatalogItemUserActionMessageDefinition(
                        catalogItemId, action, id, placeholdersValues)
        );
    }

}
