package org.opendevstack.component_provisioner.server.controllers.model.awx;

import org.opendevstack.component_provisioner.server.model.ProvisionActionResponseMother;
import org.springframework.http.HttpStatusCode;


public class AwxResponseMother {

    public static AwxResponse of() {
        return AwxResponse.builder()
                .httpStatusCode(HttpStatusCode.valueOf(200))
                .awxResponseBody(ProvisionActionResponseMother.of())
                .build();
    }
}
