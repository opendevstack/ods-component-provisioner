package org.opendevstack.component_provisioner.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.controllers.exceptions.*;
import org.opendevstack.component_provisioner.server.model.RestErrorMessage;
import org.opendevstack.component_provisioner.server.services.exceptions.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;

    @Test
    void givenGenericException_whenHandleAllExceptionsIsCalled_thenReturnsInternalServerError() {
        // given
        Exception ex = new Exception("Generic error");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleAllExceptions(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody().getMessage()).isEqualTo("Generic error");
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_PROBLEM_JSON);
    }

    @Test
    void givenMissingServletRequestParameterException_whenHandleRequestParamsExceptionsIsCalled_thenReturnsBadRequest() {
        // given
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("param", "String");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleRequestParamsExceptions(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).contains("Required request parameter 'param' for method parameter type String is not present");
    }

    @Test
    void givenMethodArgumentTypeMismatchException_whenHandleRequestParamsExceptionsIsCalled_thenReturnsBadRequest() {
        // given
        MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleRequestParamsExceptions(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void givenBadRequestException_whenHandleBadRequestExceptionIsCalled_thenReturnsBadRequest() {
        // given
        BadRequestException ex = new BadRequestException("Bad request");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleBadRequestException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo("Bad request");
    }

    @Test
    void givenRestEntityNotFoundException_whenHandleEntityNotFoundExceptionIsCalled_thenReturnsNotFound() {
        // given
        RestEntityNotFoundException ex = new RestEntityNotFoundException("Not found");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleEntityNotFoundException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Not found");
    }

    @Test
    void givenInvalidRestEntityException_whenHandleInvalidEntityExceptionIsCalled_thenReturnsUnprocessableEntity() {
        // given
        InvalidRestEntityException ex = new InvalidRestEntityException("Invalid entity");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleInvalidEntityException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(response.getBody().getMessage()).isEqualTo("Invalid entity");
    }

    @Test
    void givenProjectComponentAlreadyProvisionedException_whenHandleProjectComponentAlreadyProvisionedExceptionIsCalled_thenReturnsConflict() {
        // given
        ProjectComponentAlreadyProvisionedException ex = new ProjectComponentAlreadyProvisionedException("Already provisioned");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleProjectComponentAlreadyProvisionedException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody().getMessage()).isEqualTo("Already provisioned");
    }

    @Test
    void givenProjectConfigurationException_whenHandleProjectConfigurationExceptionIsCalled_thenReturnsUnprocessableEntity() {
        // given
        ProjectConfigurationException ex = new ProjectConfigurationException("Config error");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleProjectConfigurationException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(response.getBody().getMessage()).isEqualTo("Config error");
    }

    @Test
    void givenUserNotAllowedException_whenHandleUserNotAllowedExceptionIsCalled_thenReturnsForbidden() {
        // given
        UserNotAllowedException ex = new UserNotAllowedException("Not allowed");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleUserNotAllowedException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody().getMessage()).isEqualTo("Not allowed");
    }

    @Test
    void givenSlugNotFoundException_whenHandleSlugNotFoundExceptionIsCalled_thenReturnsNotFound() {
        // given
        SlugNotFoundException ex = new SlugNotFoundException("Slug not found");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleSlugNotFoundException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Slug not found");
    }

    @Test
    void givenComponentNotFoundException_whenHandleComponentNotFoundExceptionIsCalled_thenReturnsNotFound() {
        // given
        ComponentNotFoundException ex = new ComponentNotFoundException("Component not found");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleComponentNotFoundException(ex);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Component not found");
    }

    @Test
    void givenInvalidIdException_whenHandleInvalidIdExceptionIsCalled_thenReturnsBadRequest() {
        // given
        InvalidIdException ex = new InvalidIdException("some-id");

        // when
        ResponseEntity<RestErrorMessage> response = controllerExceptionHandler.handleInvalidIdException(ex);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().value()).isEqualTo(400);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).contains("Invalid id: some-id");
    }
}
