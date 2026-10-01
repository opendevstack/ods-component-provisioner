package org.opendevstack.component_provisioner.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.facade.ProjectComponentsApiFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProjectComponentsApiControllerTest {

    @Mock
    private ProjectComponentsApiFacade projectComponentsApiFacade;

    @InjectMocks
    private ProjectComponentsApiController controller;

    @Test
    void givenProjectKeyComponentIdAndParameters_whenUpdateProjectComponentParameters_thenDelegatesAndReturnsNoContent() {
        // given
        var projectKey = "TEST";
        var componentId = "comp-123";
        Map<String, List<String>> requestBody = Map.of("parameter", List.of("value"));

        // when
        ResponseEntity<Void> result = controller.updateProjectComponentParameters(projectKey, componentId, requestBody);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isNull();
        verify(projectComponentsApiFacade).updateProjectComponentParameters(projectKey, componentId, requestBody);
    }

    @Test
    void givenNullRequestBody_whenUpdateProjectComponentParameters_thenDelegatesNullBody() {
        // given
        var projectKey = "TEST";
        var componentId = "comp-123";

        // when
        ResponseEntity<Void> result = controller.updateProjectComponentParameters(projectKey, componentId, null);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isNull();
        verify(projectComponentsApiFacade).updateProjectComponentParameters(projectKey, componentId, null);
    }

    @Test
    void givenProjectKeyComponentIdAndParameterNames_whenDeleteProjectComponentParameters_thenDelegatesAndReturnsNoContent() {
        // given
        var projectKey = "TEST";
        var componentId = "comp-123";
        List<String> requestBody = Arrays.asList("param1", "param2");

        // when
        ResponseEntity<Void> result = controller.deleteProjectComponentParameters(projectKey, componentId, requestBody);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(result.getBody()).isNull();
        verify(projectComponentsApiFacade).deleteProjectComponentParameters(projectKey, componentId, requestBody);
    }

    @Test
    void givenNullRequestBodyForDelete_whenDeleteProjectComponentParameters_thenDelegatesNullBody() {
        // given
        var projectKey = "TEST";
        var componentId = "comp-123";

        // when
        ResponseEntity<Void> result = controller.deleteProjectComponentParameters(projectKey, componentId, null);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(result.getBody()).isNull();
        verify(projectComponentsApiFacade).deleteProjectComponentParameters(projectKey, componentId, null);
    }
}
