package org.opendevstack.component_provisioner.server.controllers.validators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserAction;
import org.opendevstack.component_provisioner.server.controllers.exceptions.UserNotAllowedException;
import org.opendevstack.component_provisioner.server.controllers.model.ActionType;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class UserPermissionsValidator {

    public void validate(CatalogItem catalogItem) {
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
