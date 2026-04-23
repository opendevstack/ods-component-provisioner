package org.opendevstack.component_provisioner.server.model;

import org.opendevstack.component_provisioner.client.ods_api_server.v1.model.CreateProjectResponse;

public class CreateProjectResponseMother {

    public static CreateProjectResponse of() {
        return CreateProjectResponse.builder().build();
    }

    public static CreateProjectResponse of(String projectKey) {
        return CreateProjectResponse.builder()
                .projectKey(projectKey)
                .build();

    }
}
