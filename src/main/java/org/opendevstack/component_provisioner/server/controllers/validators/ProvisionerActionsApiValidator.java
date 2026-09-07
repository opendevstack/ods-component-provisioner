package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserAction;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.exceptions.UserNotAllowedException;
import org.opendevstack.component_provisioner.server.controllers.model.ActionType;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiValidator {

    private static final Set<String> INTERNAL_PROVISIONING_PARAMS = Set.of("catalog_item_id", "project_key");

    private final MandatoryFieldsValidator mandatoryFieldsValidator;
    private final ComponentsValidator componentsValidator;
    private final InputParamsValidator inputParamsValidator;

    public void validate(ProvisionAction provisionAction) {
        log.debug("Start validation for provisionActions: {}", provisionAction);

        inputParamsValidator.validate(provisionAction);
        componentsValidator.validate(provisionAction);
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
                        log.debug("The parameter '{}' is not defined at catalog item level. Due to that, it is not allowed when provisioning '{}'.", param.getName(), catalogItem.getTitle());

                        throw new InvalidRestEntityException(
                                String.format("The parameter '%s' is not allowed when provisioning '%s'.", param.getName(), catalogItem.getTitle())
                        );
                    }
                });
    }

    public void validateMandatoryFields(ProvisionAction provisionAction, CatalogItem catalogItem) {
        mandatoryFieldsValidator.validate(provisionAction, catalogItem);
    }

    public void validateUserHasPermissionsToProvision(CatalogItem catalogItem) {
        log.debug("Validating user has permissions to provision. CatalogItem: {}", catalogItem);

        boolean provisionIsRequestable = Optional.ofNullable(catalogItem)
                .map(CatalogItem::getUserActions)
                .stream()
                .flatMap(Collection::stream)
                .filter(action -> ActionType.PROVISION.getValue().equals(action.getId()))
                .findFirst()
                .map(CatalogItemUserAction::getRequestable)
                .orElse(false);

        if (!provisionIsRequestable) {
            String message = "User does not have permissions to provision this component.";

            throw new UserNotAllowedException(message);
        }
    }

}
