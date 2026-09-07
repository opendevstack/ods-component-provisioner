package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserAction;
import org.opendevstack.component_provisioner.server.controllers.exceptions.UserNotAllowedException;
import org.opendevstack.component_provisioner.server.controllers.model.ActionType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserPermissionsValidatorTest {

    private final UserPermissionsValidator userPermissionsValidator = new UserPermissionsValidator();

    @Test
    void givenNullCatalogItem_whenValidatingUserPermissions_thenThrowsUserNotAllowedException() {
        // when / then
        assertThatThrownBy(() -> userPermissionsValidator.validate(null))
                .isInstanceOf(UserNotAllowedException.class)
                .hasMessage("User does not have permissions to provision this component.");
    }

    @Test
    void givenCatalogItemWithoutUserActions_whenValidatingUserPermissions_thenThrowsUserNotAllowedException() {
        // given
        var catalogItem = CatalogItem.builder().title("My Item").userActions(null).build();

        // when / then
        assertThatThrownBy(() -> userPermissionsValidator.validate(catalogItem))
                .isInstanceOf(UserNotAllowedException.class)
                .hasMessage("User does not have permissions to provision this component.");
    }

    @Test
    void givenCatalogItemWithoutProvisionUserAction_whenValidatingUserPermissions_thenThrowsUserNotAllowedException() {
        // given
        var deleteAction = CatalogItemUserAction.builder().id("DELETE").requestable(true).build();
        var catalogItem = CatalogItem.builder().title("My Item").userActions(List.of(deleteAction)).build();

        // when / then
        assertThatThrownBy(() -> userPermissionsValidator.validate(catalogItem))
                .isInstanceOf(UserNotAllowedException.class)
                .hasMessage("User does not have permissions to provision this component.");
    }

    @Test
    void givenProvisionActionNotRequestable_whenValidatingUserPermissions_thenThrowsUserNotAllowedException() {
        // given
        var provisionAction = CatalogItemUserAction.builder()
                .id(ActionType.PROVISION.getValue())
                .requestable(false)
                .build();
        var catalogItem = CatalogItem.builder().title("My Item").userActions(List.of(provisionAction)).build();

        // when / then
        assertThatThrownBy(() -> userPermissionsValidator.validate(catalogItem))
                .isInstanceOf(UserNotAllowedException.class)
                .hasMessage("User does not have permissions to provision this component.");
    }

    @Test
    void givenProvisionActionWithNullRequestable_whenValidatingUserPermissions_thenThrowsUserNotAllowedException() {
        // given
        var provisionAction = CatalogItemUserAction.builder()
                .id(ActionType.PROVISION.getValue())
                .requestable((Boolean) null)
                .build();
        var catalogItem = CatalogItem.builder().title("My Item").userActions(List.of(provisionAction)).build();

        // when / then
        assertThatThrownBy(() -> userPermissionsValidator.validate(catalogItem))
                .isInstanceOf(UserNotAllowedException.class)
                .hasMessage("User does not have permissions to provision this component.");
    }

    @Test
    void givenProvisionActionRequestable_whenValidatingUserPermissions_thenDoesNotThrow() {
        // given
        var provisionAction = CatalogItemUserAction.builder()
                .id(ActionType.PROVISION.getValue())
                .requestable(true)
                .build();
        var catalogItem = CatalogItem.builder().title("My Item").userActions(List.of(provisionAction)).build();

        // when / then
        assertThatNoException().isThrownBy(() -> userPermissionsValidator.validate(catalogItem));
    }
}

