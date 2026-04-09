package org.opendevstack.component_provisioner.server.model;

import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserAction;

public class CatalogItemUserActionMother {

    public static CatalogItemUserAction of() {
        return CatalogItemUserAction.builder()
                .id("PROVISION")
                .build();
    }
}
