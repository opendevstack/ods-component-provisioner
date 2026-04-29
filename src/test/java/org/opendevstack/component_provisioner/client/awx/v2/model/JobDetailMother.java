package org.opendevstack.component_provisioner.client.awx.v2.model;

import java.util.Map;

public class JobDetailMother {

    public static JobDetail of() {
        return of(12345, Map.of("key1", "value1", "key2", "value2"));
    }

    public static JobDetail of(Integer id, Map<String, String> artifacts) {
        return JobDetail.builder()
                .id(id)
                .artifacts(artifacts)
                .build();
    }
}
