package org.opendevstack.component_provisioner.config;

import org.opendevstack.component_provisioner.server.controllers.exceptions.*;
import org.opendevstack.component_provisioner.server.model.RestErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.services.exceptions.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handleAllExceptions(Exception ex) {
        log.error("Unhandled exception", ex);
        return defaultErrResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    public ResponseEntity<RestErrorMessage> handleMissingServletRequestParameterException(Exception ex) {
        return defaultErrResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestErrorMessage> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        var propertyName = ex.getPropertyName();
        return defaultErrResponse(
                String.format(
                        "Invalid request parameter: %s.",
                        propertyName),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestErrorMessage> handleBadRequestException(BadRequestException ex) {
        return defaultErrResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestEntityNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleEntityNotFoundException(RestEntityNotFoundException ex) {
        return defaultErrResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRestEntityException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidEntityException(InvalidRestEntityException ex) {
        return defaultErrResponse(ex, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ProjectComponentAlreadyProvisionedException.class)
    public ResponseEntity<RestErrorMessage> handleProjectComponentAlreadyProvisionedException(ProjectComponentAlreadyProvisionedException ex) {
        return defaultErrResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProjectConfigurationException.class)
    public ResponseEntity<RestErrorMessage> handleProjectConfigurationException(ProjectConfigurationException ex) {
        return defaultErrResponse(ex, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserNotAllowedException.class)
    public ResponseEntity<RestErrorMessage> handleUserNotAllowedException(UserNotAllowedException ex) {
        return defaultErrResponse(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SlugNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleSlugNotFoundException(SlugNotFoundException ex) {
        return defaultErrResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ComponentNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleComponentNotFoundException(ComponentNotFoundException ex) {
        return defaultErrResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidIdException(InvalidIdException ex) {
        return defaultErrResponse(ex, HttpStatus.BAD_REQUEST);
    }

    private static ResponseEntity<RestErrorMessage> defaultErrResponse(Exception ex, HttpStatus errStatus) {
        // Explicitly setting MediaType.APPLICATION_JSON contentType is required, 
        // due to clients sending miscellaneous Accept headers on the request, 
        // but error messages are always in JSON format
        log.debug("Handling exception", ex);

        return ResponseEntity
                .status(errStatus)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(defaultErrMsg(ex));
    }

    private static ResponseEntity<RestErrorMessage> defaultErrResponse(String errorMessage, HttpStatus errStatus) {
        // Explicitly setting MediaType.APPLICATION_JSON contentType is required,
        // due to clients sending miscellaneous Accept headers on the request,
        // but error messages are always in JSON format
        return ResponseEntity
                .status(errStatus)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(new RestErrorMessage(errorMessage));
    }

    private static RestErrorMessage defaultErrMsg(Exception ex) {
        return new RestErrorMessage(ex.getMessage());
    }
}
