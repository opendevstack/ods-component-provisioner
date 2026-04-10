package org.opendevstack.component_provisioner.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.facade.ProvisionerMessagesDefinitionsApiFacade;
import org.opendevstack.component_provisioner.server.model.ProvisionerMessageDefinition;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerMessagesDefinitionsApiControllerTest {

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private ProvisionerMessagesDefinitionsApiFacade provisionerMessagesDefinitionsApiFacade;

    @InjectMocks
    private ProvisionerMessagesDefinitionsApiController controller;

    @Test
    void getMessageDefinitionByCatalogItemIdAndMessageId_callsFacade() {
        String catalogItemId = "cat-1";
        String action = "create";
        String id = "msg-1";
        Map<String, String> placeholders = Map.of("key", "value");
        ProvisionerMessageDefinition definition = new ProvisionerMessageDefinition();
        ResponseEntity<ProvisionerMessageDefinition> responseEntity = ResponseEntity.ok(definition);

        when(provisionerMessagesDefinitionsApiFacade.getMessageDefinition(any(Supplier.class)))
                .thenReturn(responseEntity);

        ResponseEntity<ProvisionerMessageDefinition> result = controller.getMessageDefinitionByCatalogItemIdAndMessageId(
                catalogItemId, action, id, placeholders);

        assertThat(result).isEqualTo(responseEntity);
        verify(provisionerMessagesDefinitionsApiFacade).getMessageDefinition(any(Supplier.class));
    }
}
