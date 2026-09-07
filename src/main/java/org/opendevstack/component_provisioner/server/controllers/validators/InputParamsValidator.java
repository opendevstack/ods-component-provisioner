package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class InputParamsValidator {

    public void validateInputParams(String projectKey, String accessToken, String componentId) {
        log.debug("Validating input params. projectKey: {}, accessToken: {}, componentId: {}", projectKey, accessToken, componentId);

        if (StringUtils.isBlank(projectKey) || StringUtils.isBlank(accessToken) || StringUtils.isBlank(componentId)) {
            throw new InvalidRestEntityException("project_key, access_token, component_id are required.");
        }
    }
}
