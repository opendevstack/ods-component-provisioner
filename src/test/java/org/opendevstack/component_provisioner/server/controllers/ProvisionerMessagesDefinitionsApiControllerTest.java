package org.opendevstack.component_provisioner.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.facade.ProvisionerMessagesDefinitionsApiFacade;
import org.opendevstack.component_provisioner.server.model.ProvisionerMessageDefinition;
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
    private ProvisionerMessagesDefinitionsApiFacade provisionerMessagesDefinitionsApiFacade;

    @InjectMocks
    private ProvisionerMessagesDefinitionsApiController controller;

    @Test
    void givenValidCatalogItemAndMessageId_whenGetMessageDefinitionByCatalogItemIdAndMessageId_thenDelegatesToFacade() {
        // given
        var catalogItemId = "cat-1";
        var action = "create";
        var id = "msg-1";
        var placeholders = Map.of("key", "value");
        var definition = new ProvisionerMessageDefinition();
        var responseEntity = ResponseEntity.ok(definition);

        when(provisionerMessagesDefinitionsApiFacade.getMessageDefinition(any(Supplier.class)))
                .thenReturn(responseEntity);

        // when
        ResponseEntity<ProvisionerMessageDefinition> result = controller.getMessageDefinitionByCatalogItemIdAndMessageId(
                catalogItemId, action, id, placeholders);

        // then
        assertThat(result).isEqualTo(responseEntity);
        verify(provisionerMessagesDefinitionsApiFacade).getMessageDefinition(any(Supplier.class));
    }
}
