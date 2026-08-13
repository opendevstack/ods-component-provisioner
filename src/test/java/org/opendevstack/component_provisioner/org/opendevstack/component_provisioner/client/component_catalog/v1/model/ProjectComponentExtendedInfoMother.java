package org.opendevstack.component_provisioner.org.opendevstack.component_provisioner.client.component_catalog.v1.model;

import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatus;

public class ProjectComponentExtendedInfoMother {

    public static ProjectComponentExtendedInfo of() {
        return of(ProvisioningStatus.CREATED);
    }

    public static ProjectComponentExtendedInfo of(ProvisioningStatus status) {
        return of("comp-123", status);
    }

    public static ProjectComponentExtendedInfo of(String componentId, ProvisioningStatus status) {
        return ProjectComponentExtendedInfo.builder()
                .componentId(componentId)
                .status(status)
                .workflowJobId("12345")
                .build();
    }
}
