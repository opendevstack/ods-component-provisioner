package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getCatalogItemId;
import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getProjectKey;
import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.getLocation;

@Service
@AllArgsConstructor
@Slf4j
public class MandatoryFieldsValidator {

    private final ComponentCatalogService componentCatalogService;
    private final AuthenticationProvider authenticationProvider;

    public void validate(ProvisionAction provisionAction) {
        var projectKey = getProjectKey(provisionAction);
        var catalogItemId = getCatalogItemId(provisionAction);
        var accessToken = authenticationProvider.getAccessToken();

        var catalogItem = componentCatalogService.getCatalogItem(accessToken, catalogItemId, projectKey);
        var provisionUserAction = Optional.ofNullable(catalogItem)
                .map(CatalogItem::getUserActions)
                .map(userActions -> userActions.stream()
                        .filter(userAction -> "PROVISION".equals(userAction.getId()))
                        .findFirst()
                        .orElseThrow(() -> new InvalidRestEntityException("The catalog item doesn't have a PROVISION user action")))
                .orElseThrow(() -> new InvalidRestEntityException("The catalog item does not exist, or doesn't have any user action"));

        Map<String, CatalogItemUserActionParameter> mandatoryFields =
                Optional.ofNullable(provisionUserAction.getParameters())
                        .map(parameters -> parameters.stream()
                                .filter(userActionParameter -> Boolean.TRUE.equals(userActionParameter.getRequired()))
                                .collect(Collectors.toMap(
                                        CatalogItemUserActionParameter::getName,   // key
                                        Function.identity()                         // value
                                )))
                        .orElse(Collections.emptyMap());

        mandatoryFields.keySet().forEach(mandatoryFieldName -> {
            var param = provisionAction.getParameters().stream()
                    .filter(p -> Objects.equals(p.getName(), mandatoryFieldName))
                    .findFirst()
                    .orElseThrow(() -> new InvalidRestEntityException(String.format(
                            "The parameter %s is mandatory but was not provided in the request.",
                            mandatoryFieldName
                    )));

            validateParam(param, mandatoryFields.get(mandatoryFieldName));
        });
    }

    private void validateParam(ProvisionActionParameter param, CatalogItemUserActionParameter catalogParam) {
        if (isBlankValue(param)) {
            throw new InvalidRestEntityException(String.format(
                    "The parameter %s is mandatory and no value was provided.",
                    param.getName()
            ));
        }

        if (hasNoOptions(catalogParam)) {
            logNoOptions(param);
            return;
        }

        validateAgainstOptions(param, catalogParam);
    }


    private boolean isBlankValue(ProvisionActionParameter param) {
        Object value = param.getValue();

        if (value == null) {
            return true;
        }

        if (value instanceof List<?> list) {
            return list.isEmpty();
        }

        return StringUtils.isBlank(value.toString());
    }

    private boolean hasNoOptions(CatalogItemUserActionParameter param) {
        return param.getOptions() == null || param.getOptions().isEmpty();
    }

    private boolean isListTypeAnswer(ProvisionActionParameter param) {
        // MULTIPLELIST is the only type that stores multiple values as a list for the answers;
        // SINGLELIST and STRING types store single string values for the answer.
        return MandatoryFieldType.MULTIPLELIST.getValue().equalsIgnoreCase(param.getType());
    }

    private void logNoOptions(ProvisionActionParameter param) {
        log.debug(
                "No options for default parameter, ignoring validation of the parameter value against options. " +
                        "parameterName: {}, parameterValue: {}",
                param.getName(),
                param.getValue()
        );
    }

    private void validateAgainstOptions(
            ProvisionActionParameter param,
            CatalogItemUserActionParameter catalogParam
    ) {
        if (isListTypeAnswer(param)) {
            validateListValues(param, catalogParam);
        } else {
            validateSingleValue(param, catalogParam);
        }
    }

    @SuppressWarnings("unchecked")
    private void validateListValues(
            ProvisionActionParameter param,
            CatalogItemUserActionParameter catalogParam
    ) {
        List<String> values = (List<String>) param.getValue();

        for (String value : values) {
            if (!Objects.requireNonNull(catalogParam.getOptions()).contains(value)) {
                throw invalidValueException(param, value, catalogParam.getOptions());
            }
        }
    }

    private void validateSingleValue(
            ProvisionActionParameter param,
            CatalogItemUserActionParameter catalogParam
    ) {
        String value = param.getValue().toString();

        if (!Objects.requireNonNull(catalogParam.getOptions()).contains(value)) {
            throw invalidValueException(param, value, catalogParam.getOptions());
        }
    }

    private InvalidRestEntityException invalidValueException(
            ProvisionActionParameter param,
            String value,
            List<String> options
    ) {
        return new InvalidRestEntityException(String.format(
                "The value %s is not valid for the parameter %s. Valid values are: %s",
                value, param.getName(), options
        ));
    }

}
