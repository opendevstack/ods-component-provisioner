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

    public void validate(ProvisionAction provisionAction) {
        log.debug("Start validation for provisionActions: {}", provisionAction);

        var projectKey = getProjectKey(provisionAction);
        var accessToken = getAccessToken(provisionAction);
        var componentId = getComponentId(provisionAction);
        var idToken = authenticationProvider.getIdToken();

        validateInputParams(projectKey, accessToken, componentId);

        validateComponentIsNotProvisioned(projectKey, idToken, accessToken, componentId);

        validateUserHasPermissionsToProvision(projectKey, idToken, accessToken);
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

    private String getParameterString(ProvisionAction provisionAction, String parameterName) {
        return provisionAction.getParameters().stream()
                .filter(parameter -> parameterName.equals(parameter.getName()))
                .map(ProvisionActionParameter::getValue)
                .map(Object::toString)
                .findAny()
                .orElse(Strings.EMPTY);
    }

}
