package org.opendevstack.component_provisioner.org.opendevstack.component_provisioner.client.component_catalog.v1.model;

import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;

public class ProjectComponentExtendedInfoMother {

    public static ProjectComponentExtendedInfo of() {
        return of("PROVISIONED");
    }

    public static ProjectComponentExtendedInfo of(String status) {
        return of("comp-123", status);
    }

    public static ProjectComponentExtendedInfo of(String componentId, String status) {
        return ProjectComponentExtendedInfo.builder()
                .componentId(componentId)
                .status(status)
                .workflowJobId("12345")
                .build();
    }
}
