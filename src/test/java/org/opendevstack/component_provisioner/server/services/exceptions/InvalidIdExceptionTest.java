package org.opendevstack.component_provisioner.server.services.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InvalidIdExceptionTest {

    @Test
    void givenId_whenConstructorIsCalled_thenMessageIsCorrect() {
        // given
        String id = "some-id";

        // when
        InvalidIdException exception = new InvalidIdException(id);

        // then
        assertThat(exception.getMessage()).contains("Invalid id: some-id");
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void givenIdAndCause_whenConstructorIsCalled_thenMessageAndCauseAreCorrect() {
        // given
        String id = "some-id";
        Exception cause = new RuntimeException("root cause");

        // when
        InvalidIdException exception = new InvalidIdException(id, cause);

        // then
        assertThat(exception.getMessage()).contains("Invalid id: some-id");
        assertThat(exception.getCause()).isSameAs(cause);
    }
}
