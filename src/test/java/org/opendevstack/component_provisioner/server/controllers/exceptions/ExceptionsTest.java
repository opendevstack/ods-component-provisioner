package org.opendevstack.component_provisioner.server.controllers.exceptions;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ExceptionsTest {

    @Test
    void givenMessage_whenBadRequestExceptionIsCalled_thenMessageIsStored() {
        // given
        var message = "Bad Request";

        // when
        var exception = new BadRequestException(message);

        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenRestEntityNotFoundExceptionIsCalled_thenMessageIsStored() {
        // given
        var message = "Not Found";

        // when
        var exception = new RestEntityNotFoundException(message);

        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenInvalidRestEntityExceptionIsCalled_thenMessageIsStored() {
        // given
        var message = "Invalid Entity";

        // when
        var exception = new InvalidRestEntityException(message);

        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenProjectComponentAlreadyProvisionedExceptionIsCalled_thenMessageIsStored() {
        // given
        var message = "Already Provisioned";

        // when
        var exception = new ProjectComponentAlreadyProvisionedException(message);

        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenProjectConfigurationExceptionIsCalled_thenMessageIsStored() {
        // given
        var message = "Config Error";

        // when
        var exception = new ProjectConfigurationException(message);

        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenUserNotAllowedExceptionIsCalled_thenMessageIsStored() {
        // given
        var message = "Not Allowed";

        // when
        var exception = new UserNotAllowedException(message);

        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenSlugNotFoundExceptionIsCalled_thenMessageIsStored() {
        // given
        var message = "Slug Not Found";

        // when
        var exception = new SlugNotFoundException(message);

        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }
}
