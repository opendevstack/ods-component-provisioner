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
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerMessagesDefinitionsApiFacadeTest {

    @Mock private EntitiesMapper entitiesMapper;
    @Mock private ComponentCatalogService componentCatalogService;

    @InjectMocks private ProvisionerMessagesDefinitionsApiFacade facade;

    @Test
    void givenPresentMessageDefinition_whenGetMessageDefinition_thenMapsAndReturnsOk() {
        // given
        var catalogDef = new CatalogItemUserActionMessageDefinition();
        var mapped = new ProvisionerMessageDefinition();
        Supplier<Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>>> supplier =
                () -> Pair.of(HttpStatus.OK, Optional.of(catalogDef));

        when(entitiesMapper.asProvisionerMessageDefinition(catalogDef)).thenReturn(mapped);

        // when
        var response = facade.getMessageDefinition(supplier);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(mapped);
        verify(entitiesMapper).asProvisionerMessageDefinition(any(CatalogItemUserActionMessageDefinition.class));
    }

    @Test
    void givenEmptyMessageDefinition_whenGetMessageDefinition_thenReturnsNoContentWithNullBody() {
        // given
        Supplier<Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>>> supplier =
                () -> Pair.of(HttpStatus.NO_CONTENT, Optional.empty());

        // when
        var response = facade.getMessageDefinition(supplier);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void givenValidParams_whenGetCatalogItemUserActionMessageDefinitionIsCalled_thenDelegatesToService() {
        // given
        var catalogItemId = "cat-1";
        var action = "create";
        var id = "msg-1";
        var placeholders = Map.of("key", "value");
        Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>> result =
                Pair.of(HttpStatus.OK, Optional.empty());

        when(componentCatalogService.getCatalogItemUserActionMessageDefinition(catalogItemId, action, id, placeholders))
                .thenReturn(result);

        // when
        var actualResult = facade.getCatalogItemUserActionMessageDefinition(catalogItemId, action, id, placeholders);

        // then
        assertThat(actualResult).isEqualTo(result);
        verify(componentCatalogService).getCatalogItemUserActionMessageDefinition(catalogItemId, action, id, placeholders);
    }
}
