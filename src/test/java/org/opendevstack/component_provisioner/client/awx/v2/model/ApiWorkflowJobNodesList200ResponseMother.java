package org.opendevstack.component_provisioner.client.awx.v2.model;

import java.util.Collections;
import java.util.List;

public class ApiWorkflowJobNodesList200ResponseMother {

    public static ApiWorkflowJobNodesList200Response of() {
        return of(Collections.emptyList());
    }

    public static ApiWorkflowJobNodesList200Response of(List<WorkflowJobNodeList> results) {
        return ApiWorkflowJobNodesList200Response.builder()
                .results(results)
                .build();
    }
}
