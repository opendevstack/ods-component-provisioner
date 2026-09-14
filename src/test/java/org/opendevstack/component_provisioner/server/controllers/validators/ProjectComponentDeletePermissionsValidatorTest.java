package org.opendevstack.component_provisioner.server.controllers.validators;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.server.controllers.exceptions.UserNotAllowedException;
import org.opendevstack.component_provisioner.server.model.ProjectComponentExtendedInfoMother;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProjectComponentDeletePermissionsValidatorTest {

    private final ProjectComponentDeletePermissionsValidator validator = new ProjectComponentDeletePermissionsValidator();

    @Test
    public void givenProjectComponentNotDeletable_whenValidate_thenThrowsUserNotAllowedException() {
        // given
        ProjectComponentExtendedInfo projectComponentExtendedInfo = ProjectComponentExtendedInfoMother.valid();
        projectComponentExtendedInfo.setCanBeDeleted(false);

        // when / then
        assertThrows(UserNotAllowedException.class, () -> validator.validate(projectComponentExtendedInfo));
    }

    @Test
    public void givenProjectComponentDeletable_whenValidate_thenDoesNotThrow() {
        // given
        ProjectComponentExtendedInfo projectComponentExtendedInfo = ProjectComponentExtendedInfoMother.valid();

        // when / then
        validator.validate(projectComponentExtendedInfo);
    }
}
