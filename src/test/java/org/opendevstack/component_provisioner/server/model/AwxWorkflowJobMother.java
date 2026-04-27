package org.opendevstack.component_provisioner.server.model;

import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;

public class AwxWorkflowJobMother {

    public static AwxWorkflowJob of() {
        return AwxWorkflowJob.builder()
                .id(1)
                .failed(false)
                .build();
    }
}
