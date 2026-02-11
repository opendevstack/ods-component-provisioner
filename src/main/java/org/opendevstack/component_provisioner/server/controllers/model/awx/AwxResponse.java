package org.opendevstack.component_provisioner.server.controllers.model.awx;

import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import lombok.Builder;
import org.springframework.http.HttpStatusCode;

@Builder
public record AwxResponse(HttpStatusCode httpStatusCode, ProvisionActionResponse awxResponseBody) {
}
