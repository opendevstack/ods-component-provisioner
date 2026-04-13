package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.server.controllers.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator.getAccessToken;
import static org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator.getCatalogItemId;
import static org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator.getParameterString;
import static org.opendevstack.component_provisioner.server.controllers.validators.ProvisionerActionsApiValidator.getProjectKey;

@Service
@AllArgsConstructor
@Slf4j
public class MandatoryFieldsValidator {

    private final ComponentCatalogService componentCatalogService;
    private final AuthenticationProvider authenticationProvider;

    public void validate(ProvisionAction provisionAction) {
        var projectKey = getProjectKey(provisionAction);
        var accessToken = getAccessToken(provisionAction);
        var catalogItemId = getCatalogItemId(provisionAction);
        var idToken = authenticationProvider.getIdToken();
        var location = getLocation(provisionAction);

        var catalogItem = componentCatalogService.getCatalogItem(idToken, accessToken, catalogItemId, projectKey);
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

        provisionAction.getParameters().stream()
                .filter(param -> mandatoryFields.containsKey(param.getName()))
                .forEach(param -> updateParam(param, mandatoryFields.get(param.getName()), location));
    }

    public void updateParam(
            ProvisionActionParameter param,
            CatalogItemUserActionParameter catalogParam,
            String location
    ) {

        if (isBlankValue(param)) {
            applyDefaultValue(param, catalogParam, location);
        }

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
        return param.getValue() == null ||
                StringUtils.isBlank(param.getValue().toString());
    }

    private boolean hasNoOptions(CatalogItemUserActionParameter param) {
        return param.getOptions() == null || param.getOptions().isEmpty();
    }

    private boolean isListType(ProvisionActionParameter param) {
        return MandatoryFieldType.SINGLELIST.getValue().equalsIgnoreCase(param.getType())
                || MandatoryFieldType.MULTIPLELIST.getValue().equalsIgnoreCase(param.getType());
    }

    private void applyDefaultValue(
            ProvisionActionParameter param,
            CatalogItemUserActionParameter catalogParam,
            String location
    ) {
        // STRING - SINGLELIST
        if (ParameterType.STRING.getValue().equalsIgnoreCase(param.getType())
        || MandatoryFieldType.SINGLELIST.getValue().equalsIgnoreCase(param.getType())) {
            if (StringUtils.isNotBlank(catalogParam.getDefaultValue())) {
                param.setValue(List.of(catalogParam.getDefaultValue()));
            } else if (catalogParam.getLocations() != null && !catalogParam.getLocations().isEmpty()) {
                catalogParam.getLocations().stream()
                        .filter(parameterLocation -> StringUtils.equalsIgnoreCase(parameterLocation.getLocation(), location))
                        .findFirst()
                        .ifPresent(loc -> param.setValue(List.of(loc.getValue())));
            }
            return; // no default, but that's OK
        }

        // MULTIPLELIST
        if (MandatoryFieldType.MULTIPLELIST.getValue().equalsIgnoreCase(param.getType()) && catalogParam.getDefaultValues() != null) {
            param.setValue(catalogParam.getDefaultValues());
        }
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
        if (isListType(param)) {
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

    private String getLocation(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "cluster_location");
    }
}
