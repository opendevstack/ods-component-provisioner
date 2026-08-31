package org.opendevstack.component_provisioner.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.services.restrictions.evaluators.CatalogItemUserActionGroupsRestriction;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class WhitelistedRolesService {

    private final ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;
    private final ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps catalogItemUserActionGroupsRestrictionProps;
    private final ApiClientsBuilder apiClientsBuilder;

    public CatalogItemUserActionGroupsRestriction getCatalogItemUserActionGroupsRestriction(String catalogItemId, String accessToken) {
        var apiClient = apiClientsBuilder
                .componentCatalogApiClient(accessToken, componentCatalogServiceProps.getBaseRestUrl().toString());
        var catalogItemsApi = apiClientsBuilder.catalogItemsApi(apiClient);

        var customProjectWhitelistedRoles = Optional.ofNullable(catalogItemsApi.getWhitelistedRolesByCatalogItemId(catalogItemId)).orElse(Collections.emptyList());

        CatalogItemUserActionGroupsRestriction catalogItemUserActionGroupsRestriction = CatalogItemUserActionGroupsRestriction.builder()
                .prefix(catalogItemUserActionGroupsRestrictionProps.getPrefix())
                .suffix(catalogItemUserActionGroupsRestrictionProps.getSuffix())
                .whitelistedRoles(customProjectWhitelistedRoles)
                .build();

        log.debug("CatalogItemUserActionGroupsRestriction for catalogItemId {}: {}", catalogItemId, catalogItemUserActionGroupsRestriction);

        return catalogItemUserActionGroupsRestriction;
    }

}
