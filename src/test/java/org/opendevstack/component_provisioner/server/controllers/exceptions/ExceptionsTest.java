package org.opendevstack.component_provisioner.server.controllers.exceptions;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ExceptionsTest {

    @Test
    void givenMessage_whenBadRequestExceptionIsCalled_thenMessageIsStored() {
        // given
        String message = "Bad Request";
        // when
        BadRequestException exception = new BadRequestException(message);
        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenRestEntityNotFoundExceptionIsCalled_thenMessageIsStored() {
        // given
        String message = "Not Found";
        // when
        RestEntityNotFoundException exception = new RestEntityNotFoundException(message);
        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenInvalidRestEntityExceptionIsCalled_thenMessageIsStored() {
        // given
        String message = "Invalid Entity";
        // when
        InvalidRestEntityException exception = new InvalidRestEntityException(message);
        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenProjectComponentAlreadyProvisionedExceptionIsCalled_thenMessageIsStored() {
        // given
        String message = "Already Provisioned";
        // when
        ProjectComponentAlreadyProvisionedException exception = new ProjectComponentAlreadyProvisionedException(message);
        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenProjectConfigurationExceptionIsCalled_thenMessageIsStored() {
        // given
        String message = "Config Error";
        // when
        ProjectConfigurationException exception = new ProjectConfigurationException(message);
        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenUserNotAllowedExceptionIsCalled_thenMessageIsStored() {
        // given
        String message = "Not Allowed";
        // when
        UserNotAllowedException exception = new UserNotAllowedException(message);
        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    void givenMessage_whenSlugNotFoundExceptionIsCalled_thenMessageIsStored() {
        // given
        String message = "Slug Not Found";
        // when
        SlugNotFoundException exception = new SlugNotFoundException(message);
        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }
}
