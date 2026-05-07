package org.opendevstack.component_provisioner.server.controllers.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComponentNotFoundExceptionTest {

    @Test
    void givenMessage_whenConstructorIsCalled_thenMessageIsStored() {
        // given
        String message = "Component not found";

        // when
        ComponentNotFoundException exception = new ComponentNotFoundException(message);

        // then
        assertThat(exception.getMessage()).isEqualTo(message);
    }
}
