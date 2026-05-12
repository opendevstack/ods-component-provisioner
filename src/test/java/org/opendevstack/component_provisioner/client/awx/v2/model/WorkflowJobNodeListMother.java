package org.opendevstack.component_provisioner.client.awx.v2.model;

public class WorkflowJobNodeListMother {

    public static WorkflowJobNodeList of() {
        return of(12345);
    }

    public static WorkflowJobNodeList of(Integer jobId) {
        return WorkflowJobNodeList.builder()
                .job(jobId)
                .build();
    }
}
