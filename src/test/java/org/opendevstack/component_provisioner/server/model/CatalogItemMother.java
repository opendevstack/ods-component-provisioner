package org.opendevstack.component_provisioner.server.model;

import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;

import java.util.List;

public class CatalogItemMother {

    public static CatalogItem of() {
        return CatalogItem.builder()
                .userActions(List.of(CatalogItemUserActionMother.of()))
                .build();
    }
}
