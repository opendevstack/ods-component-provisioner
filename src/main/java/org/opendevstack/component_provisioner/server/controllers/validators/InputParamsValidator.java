package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.springframework.stereotype.Service;

import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getComponentId;
import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getProjectKey;

@AllArgsConstructor
@Service
@Slf4j
public class InputParamsValidator {

    private final AuthenticationProvider authenticationProvider;

    public void validate(ProvisionAction provisionAction) throws InvalidRestEntityException {
        var projectKey = getProjectKey(provisionAction);
        var componentId = getComponentId(provisionAction);
        var accessToken = authenticationProvider.getAccessToken();

        log.debug("Validating input params. projectKey: {}, accessToken: {}, componentId: {}", projectKey, accessToken, componentId);

        if (StringUtils.isBlank(projectKey) || StringUtils.isBlank(accessToken) || StringUtils.isBlank(componentId)) {
            throw new InvalidRestEntityException("project_key, access_token, component_id are required.");
        }
    }
}
