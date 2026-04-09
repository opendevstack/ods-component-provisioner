package org.opendevstack.component_provisioner.server.facade;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionMessageDefinition;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionerMessageDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerMessagesDefinitionsApiFacadeTest {

    @Mock private EntitiesMapper entitiesMapper;

    @InjectMocks private ProvisionerMessagesDefinitionsApiFacade facade;

    @Test
    void getMessageDefinition_maps_whenPresent() {
        CatalogItemUserActionMessageDefinition catalogDef = new CatalogItemUserActionMessageDefinition();
        ProvisionerMessageDefinition mapped = new ProvisionerMessageDefinition();
        Supplier<Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>>> supplier = () -> Pair.of(HttpStatus.OK, Optional.of(catalogDef));

        when(entitiesMapper.asProvisionerMessageDefinition(catalogDef)).thenReturn(mapped);

        var response = facade.getMessageDefinition(supplier);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mapped, response.getBody());
        verify(entitiesMapper).asProvisionerMessageDefinition(any(CatalogItemUserActionMessageDefinition.class));
    }

    @Test
    void getMessageDefinition_nullBody_whenEmpty() {
        Supplier<Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>>> supplier = () -> Pair.of(HttpStatus.NO_CONTENT, Optional.empty());

        var response = facade.getMessageDefinition(supplier);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}
