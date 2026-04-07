package org.opendevstack.component_provisioner.server.services.catalog;

import org.opendevstack.component_provisioner.server.services.restrictions.evaluators.CatalogItemUserActionGroupsRestriction;

import java.util.List;

public class CatalogItemUserActionGroupsRestrictionMother {

    public static CatalogItemUserActionGroupsRestriction of() {
        return CatalogItemUserActionGroupsRestriction.builder()
                .prefix(List.of("prefix-1", "prefix-2"))
                .suffix(List.of("suffix-1", "suffix-2"))
                .build();
    }
}
