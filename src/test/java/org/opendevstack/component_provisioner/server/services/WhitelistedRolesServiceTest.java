package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemsApi;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WhitelistedRolesServiceTest {

    @Mock
    private ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps
            catalogItemUserActionGroupsRestrictionProps;

    @Mock
    private ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;

    @Mock
    private ApiClientsBuilder apiClientsBuilder;

    @Mock
    private ApiClient apiClient;

    @Mock
    private CatalogItemsApi catalogItemsApi;

    @InjectMocks
    private WhitelistedRolesService whitelistedRolesService;

    @Test
    void givenCatalogItemHasWhitelistedRoles_whenGettingRestriction_thenReturnsConfiguredRestriction() throws Exception {
        // given
        var catalogItemId = "catalog-item-1";
        var accessToken = "access-token";
        var whitelistedRoles = List.of("group-1", "group-2");
        var prefix = List.of("prefix-");
        var suffix = List.of("-suffix");
        var baseRestUrl = "http://component-catalog";

        when(catalogItemUserActionGroupsRestrictionProps.getPrefix()).thenReturn(prefix);
        when(catalogItemUserActionGroupsRestrictionProps.getSuffix()).thenReturn(suffix);
        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(URI.create(baseRestUrl).toURL());
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseRestUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.catalogItemsApi(apiClient)).thenReturn(catalogItemsApi);
        when(catalogItemsApi.getWhitelistedRolesByCatalogItemId(catalogItemId)).thenReturn(whitelistedRoles);

        // when
        var result = whitelistedRolesService.getCatalogItemUserActionGroupsRestriction(catalogItemId, accessToken);

        // then
        assertThat(result.getPrefix()).isEqualTo(prefix);
        assertThat(result.getSuffix()).isEqualTo(suffix);
        assertThat(result.getWhitelistedRoles()).isEqualTo(whitelistedRoles);
    }

    @Test
    void givenCatalogItemHasNoWhitelistedRoles_whenGettingRestriction_thenReturnsEmptyRolesList() throws Exception {
        // given
        var catalogItemId = "catalog-item-1";
        var accessToken = "access-token";
        var baseRestUrl = "http://component-catalog";

        when(catalogItemUserActionGroupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
        when(catalogItemUserActionGroupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));
        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(URI.create(baseRestUrl).toURL());
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseRestUrl)).thenReturn(apiClient);
        when(apiClientsBuilder.catalogItemsApi(apiClient)).thenReturn(catalogItemsApi);
        when(catalogItemsApi.getWhitelistedRolesByCatalogItemId(catalogItemId)).thenReturn(null);

        // when
        var result = whitelistedRolesService.getCatalogItemUserActionGroupsRestriction(catalogItemId, accessToken);

        // then
        assertThat(result.getWhitelistedRoles()).isEmpty();
    }
}

