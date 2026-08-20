package org.opendevstack.component_provisioner.server.services.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InvalidIdExceptionTest {

    @Test
    void givenId_whenConstructorIsCalled_thenMessageIsCorrect() {
        // given
        var id = "some-id";

        // when
        var exception = new InvalidIdException(id);

        // then
        assertThat(exception.getMessage()).contains("Invalid id: some-id");
        assertThat(exception.getCause()).isNull();
    }
}
