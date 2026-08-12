package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.ProjectComponentAlreadyProvisionedException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.UserNotAllowedException;
import org.opendevstack.component_provisioner.server.controllers.model.ActionType;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
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
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.opendevstack.component_provisioner.server.services.ProvisionerActionsParameterExtractor.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiValidator {

    private static final Set<String> INTERNAL_PROVISIONING_PARAMS = Set.of("catalog_item_id", "project_key");

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

        validateComponentIsNotProvisioned(projectKey, componentId);

        validateUserHasPermissionsToProvision(projectKey, accessToken);
    }

    public void validateReceivesOnlyVisibleParameters(ProvisionAction provisionAction, CatalogItem catalogItem) {
        var catalogItemProvisionUserAction = Optional.ofNullable(catalogItem)
                .map(CatalogItem::getUserActions)
                .map(userActions -> userActions.stream()
                        .filter(userAction -> ActionType.PROVISION.getValue().equals(userAction.getId()))
                        .findFirst()
                        .orElseThrow(() -> new InvalidRestEntityException("The catalog item doesn't have a PROVISION user action")))
                .orElseThrow(() -> new InvalidRestEntityException("The catalog item does not exist, or doesn't have any user action"));

        Map<String, CatalogItemUserActionParameter> catalogParamsByName = Optional.ofNullable(catalogItemProvisionUserAction.getParameters())
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toMap(CatalogItemUserActionParameter::getName, Function.identity()));

        provisionAction.getParameters()
                .forEach(param -> {
                    // Some parameters are internally added and should be accepted despite not being defined in the items
                    if (INTERNAL_PROVISIONING_PARAMS.contains(param.getName())) {
                        return;
                    }
                    var catalogParam = catalogParamsByName.get(param.getName());
                    if (catalogParam == null || !Boolean.TRUE.equals(catalogParam.getVisible())) {
                        throw new InvalidRestEntityException(
                                String.format("The parameter '%s' is not allowed when provisioning '%s'.", param.getName(), catalogItem.getTitle())
                        );
                    }
                });
    }

    public void validateMandatoryFields(ProvisionAction provisionAction, CatalogItem catalogItem) {
        mandatoryFieldsValidator.validate(provisionAction, catalogItem);
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

    private void validateComponentIsNotProvisioned(String projectKey, String componentId) {
        log.debug("Validating component is not provisioned. projectKey: {}, componentId: {}", projectKey, componentId);
        var accessToken = authenticationProvider.getAccessToken();
        var projectComponents = componentCatalogService.getProjectComponents(accessToken, projectKey);

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

    public void validateWorkflowPresence(ProvisionAction provisionAction) {
        var workflow = getWorkflow(provisionAction);
        var workflowName = getWorkflowName(provisionAction);
        var deletionWorkflow = getDeletionWorkflow(provisionAction);
        var deletionWorkflowName = getDeletionWorkflowName(provisionAction);

        log.debug("Validating presence of workflow or workflow_name. Workflow: {}, Workflow name: {}", workflow, workflowName);

        var workflowIsNotPresent = StringUtils.isBlank(workflow) && StringUtils.isBlank(workflowName);
        var deletionWorkflowIsNotPresent = StringUtils.isBlank(deletionWorkflow) && StringUtils.isBlank(deletionWorkflowName);

        if (workflowIsNotPresent || deletionWorkflowIsNotPresent) {
            throw new InvalidRestEntityException("Either workflow or workflow_name are required. Also deletion_workflow is required.");
        }
    }

}
