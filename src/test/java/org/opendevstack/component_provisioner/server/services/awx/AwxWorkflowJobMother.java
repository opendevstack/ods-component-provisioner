package org.opendevstack.component_provisioner.server.services.awx;

public class AwxWorkflowJobMother {

    public static AwxWorkflowJob of() {
        return AwxWorkflowJob.builder()
                .id(1)
                .failed(false)
                .build();
    }
}
