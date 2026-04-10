package org.opendevstack.component_provisioner.server.controllers.validators;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectComponentAlreadyProvisionedException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.UserNotAllowedException;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.ProjectsInfoService;
import org.opendevstack.component_provisioner.server.services.restrictions.evaluators.CatalogItemUserActionGroupsRestriction;
import org.opendevstack.component_provisioner.server.services.restrictions.evaluators.EvaluationRestrictions;
import org.opendevstack.component_provisioner.server.services.restrictions.evaluators.GroupsRestrictionsEvaluator;
import org.opendevstack.component_provisioner.server.services.restrictions.evaluators.RestrictionsParams;
import org.opendevstack.component_provisioner.server.services.restrictions.evaluators.UserActionEntityRestrictions;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiValidator {

    private final ComponentCatalogService componentCatalogService;
    private final AuthenticationProvider authenticationProvider;
    private final GroupsRestrictionsEvaluator groupsRestrictionsEvaluator;
    private final ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps catalogItemUserActionGroupsRestrictionProps;
    private final ProjectsInfoService projectsInfoService;

    public void validate(ProvisionAction provisionAction) {
        log.debug("Start validation for provisionActions: {}", provisionAction);

        var projectKey = getProjectKey(provisionAction);
        var accessToken = getAccessToken(provisionAction);
        var componentId = getComponentId(provisionAction);
        var idToken = authenticationProvider.getIdToken();

        validateInputParams(projectKey, accessToken, componentId);

        validateComponentIsNotProvisioned(projectKey, idToken, accessToken, componentId);

        validateUserHasPermissionsToProvision(projectKey, idToken, accessToken);

        validateMandatoryFields(provisionAction);
    }

    private void validateMandatoryFields(ProvisionAction provisionAction) {
        var projectKey = getProjectKey(provisionAction);
        var accessToken = getAccessToken(provisionAction);
        var catalogItemId = getCatalogItemId(provisionAction);
        var idToken = authenticationProvider.getIdToken();

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
                .forEach(param -> updateParam(param, mandatoryFields.get(param.getName())));
    }

    private void updateParam(@Valid ProvisionActionParameter param, CatalogItemUserActionParameter catalogItemUserActionParameter) {
        if (StringUtils.isNotBlank(param.getValue().toString())) {
            if (catalogItemUserActionParameter.getOptions() != null && !catalogItemUserActionParameter.getOptions().isEmpty()) {
                if (param.getType().equalsIgnoreCase("list") || param.getType().equalsIgnoreCase("multiplelist")) {
                    List<String> paramValues = (List<String>) param.getValue();
                    for (String value : paramValues) {
                        if (!catalogItemUserActionParameter.getOptions().contains(value)) {
                            throw new InvalidRestEntityException(String.format("The value %s is not valid for the parameter %s. Valid values are: %s",
                                    value, param.getName(), catalogItemUserActionParameter.getOptions()));
                        }
                    }
                }  else {
                    var validParamValue = catalogItemUserActionParameter.getOptions().contains(param.getValue().toString());

                    if (!validParamValue) {
                        throw new InvalidRestEntityException(String.format("The value %s is not valid for the parameter %s. Valid values are: %s",
                                param.getValue(), param.getName(), catalogItemUserActionParameter.getOptions()));
                    }
                }
            } else {
                log.debug("No options for default parameter, ignoring validation of the parameter value against options. parameterName: {}, parameterValue: {}", param.getName(), param.getValue());
            }
        } else {
            if (StringUtils.isNotBlank(catalogItemUserActionParameter.getDefaultValue())) {
                param.setValue(
                        List.of(catalogItemUserActionParameter.getDefaultValue())
                );
            } else if (catalogItemUserActionParameter.getDefaultValues() != null) {
                param.setValue(catalogItemUserActionParameter.getDefaultValues());
            } else {
                throw new InvalidRestEntityException(String.format("The parameter %s is mandatory and doesn't have a default value, please provide a value for this parameter.", param.getName()));
            }
        }
    }

    private void validateUserHasPermissionsToProvision(String projectKey, String idToken, String accessToken) {
        log.debug("Validating user has permissions to provision. projectKey: {}", projectKey);

        CatalogItemUserActionGroupsRestriction catalogItemUserActionGroupsRestriction = CatalogItemUserActionGroupsRestriction.builder()
                .prefix(catalogItemUserActionGroupsRestrictionProps.getPrefix())
                .suffix(catalogItemUserActionGroupsRestrictionProps.getSuffix())
                .build();
        UserActionEntityRestrictions userActionEntityRestrictions = UserActionEntityRestrictions.builder()
                .groups(catalogItemUserActionGroupsRestriction)
                .build();
        EvaluationRestrictions restrictions = new EvaluationRestrictions(projectKey, userActionEntityRestrictions);

        List<String> userGroups = projectsInfoService.getProjectGroups(idToken, accessToken);
        RestrictionsParams params = RestrictionsParams.builder()
                .projectKey(projectKey)
                .userGroups(userGroups)
                .build();

        var groupsEvaluationResult = groupsRestrictionsEvaluator.evaluate(restrictions, params);

        if (Boolean.FALSE.equals(groupsEvaluationResult.getLeft())) {
            throw new UserNotAllowedException(groupsEvaluationResult.getRight());
        }
    }

    private void validateComponentIsNotProvisioned(String projectKey, String idToken, String accessToken, String componentId) {
        log.debug("Validating component is not provisioned. projectKey: {}, componentId: {}", projectKey, componentId);

        var projectComponents = componentCatalogService.getProjectComponents(projectKey, idToken, accessToken);

        var componentIdAlreadyProvisioned = projectComponents.stream()
                .filter(projectComponent -> projectComponent.getComponentId() != null)
                .anyMatch(projectComponent -> projectComponent.getComponentId().equals(componentId));

        if (componentIdAlreadyProvisioned) {
            throw new ProjectComponentAlreadyProvisionedException("This component name already exists, please choose another name.");
        }
    }

    private static void validateInputParams(String projectKey, String accessToken, String componentId) {
        log.debug("Validating input params. projectKey: {}, accessToken: {}, componentId: {}", projectKey, accessToken, componentId);

        if (StringUtils.isBlank(projectKey) || StringUtils.isBlank(accessToken) || StringUtils.isBlank(componentId)) {
            throw new InvalidRestEntityException("project_key, access_token, component_id are required.");
        }
    }

    private String getComponentId(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "component_id");
    }

    private String getProjectKey(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "project_key");
    }

    private String getAccessToken(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "access_token");
    }

    private String getCatalogItemId(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "catalog_item_id");
    }

    private String getParameterString(ProvisionAction provisionAction, String parameterName) {
        return provisionAction.getParameters().stream()
                .filter(parameter -> parameterName.equals(parameter.getName()))
                .map(ProvisionActionParameter::getValue)
                .map(Object::toString)
                .findAny()
                .orElse(Strings.EMPTY);
    }

}
