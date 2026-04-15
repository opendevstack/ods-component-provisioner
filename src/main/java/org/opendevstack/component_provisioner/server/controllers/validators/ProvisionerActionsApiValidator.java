package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
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

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiValidator {

    private final ComponentCatalogService componentCatalogService;
    private final AuthenticationProvider authenticationProvider;
    private final GroupsRestrictionsEvaluator groupsRestrictionsEvaluator;
    private final ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps catalogItemUserActionGroupsRestrictionProps;
    private final ProjectsInfoService projectsInfoService;
    private final MandatoryFieldsValidator mandatoryFieldsValidator;

    public void validate(ProvisionAction provisionAction) {
        log.debug("Start validation for provisionActions: {}", provisionAction);

        var projectKey = getProjectKey(provisionAction);
        var componentId = getComponentId(provisionAction);
        var accessToken = authenticationProvider.getAccessToken();

        validateInputParams(projectKey, accessToken, componentId);

        validateComponentIsNotProvisioned(projectKey, accessToken, componentId);

        validateUserHasPermissionsToProvision(projectKey, accessToken);

        mandatoryFieldsValidator.validate(provisionAction);
    }

    private void validateUserHasPermissionsToProvision(String projectKey, String accessToken) {
        log.debug("Validating user has permissions to provision. projectKey: {}", projectKey);

        CatalogItemUserActionGroupsRestriction catalogItemUserActionGroupsRestriction = CatalogItemUserActionGroupsRestriction.builder()
                .prefix(catalogItemUserActionGroupsRestrictionProps.getPrefix())
                .suffix(catalogItemUserActionGroupsRestrictionProps.getSuffix())
                .build();
        UserActionEntityRestrictions userActionEntityRestrictions = UserActionEntityRestrictions.builder()
                .groups(catalogItemUserActionGroupsRestriction)
                .build();
        EvaluationRestrictions restrictions = new EvaluationRestrictions(projectKey, userActionEntityRestrictions);

        List<String> userGroups = projectsInfoService.getProjectGroups(accessToken);
        RestrictionsParams params = RestrictionsParams.builder()
                .projectKey(projectKey)
                .userGroups(userGroups)
                .build();

        var groupsEvaluationResult = groupsRestrictionsEvaluator.evaluate(restrictions, params);

        if (groupsEvaluationResult == null || Boolean.FALSE.equals(groupsEvaluationResult.getLeft())) {
            String message = "User does not have permissions to provision this component.";

            if (groupsEvaluationResult != null && groupsEvaluationResult.getRight() != null) {
                message = groupsEvaluationResult.getRight();
            }

            throw new UserNotAllowedException(message);
        }
    }

    private void validateComponentIsNotProvisioned(String projectKey, String accessToken, String componentId) {
        log.debug("Validating component is not provisioned. projectKey: {}, componentId: {}", projectKey, componentId);

        var projectComponents = componentCatalogService.getProjectComponents(projectKey, accessToken);

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

    protected static String getComponentId(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "component_id");
    }

    protected static String getProjectKey(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "project_key");
    }

    protected static String getAccessToken(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "access_token");
    }

    protected static String getCatalogItemId(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "catalog_item_id");
    }

    protected static String getParameterString(ProvisionAction provisionAction, String parameterName) {
        return provisionAction.getParameters().stream()
                .filter(parameter -> parameterName.equals(parameter.getName()))
                .map(ProvisionActionParameter::getValue)
                .map(Object::toString)
                .findAny()
                .orElse(Strings.EMPTY);
    }

}
