package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiValidator {

    private final MandatoryFieldsValidator mandatoryFieldsValidator;
    private final ComponentsValidator componentsValidator;
    private final InputParamsValidator inputParamsValidator;

    public void validate(ProvisionAction provisionAction) {
        log.debug("Start validation for provisionActions: {}", provisionAction);

        inputParamsValidator.validate(provisionAction);
        componentsValidator.validate(provisionAction);
    }

}
