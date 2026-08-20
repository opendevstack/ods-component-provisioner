package org.opendevstack.component_provisioner.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EitherTest {

    @Test
    void givenNoError_whenConstructingEither_thenSetsValueAndFlagsCorrectly() {
        // given / when
        Either<String, Exception> either = new Either<>("ok", null);

        // then
        assertThat(either.getValue()).isEqualTo("ok");
        assertThat(either.error).isNull();
        assertThat(either.ok).isTrue();
        assertThat(either.failed).isFalse();
    }

    @Test
    void givenErrorPresent_whenConstructingEither_thenSetsErrorAndFlagsCorrectly() {
        // given
        var ex = new Exception("fail");

        // when
        Either<String, Exception> either = new Either<>(null, ex);

        // then
        assertThat(either.getValue()).isNull();
        assertThat(either.error).isEqualTo(ex);
        assertThat(either.ok).isFalse();
        assertThat(either.failed).isTrue();
    }

    @Test
    void givenErrorPresent_whenThrowError_thenThrowsRuntimeExceptionWithCause() {
        // given
        var ex = new Exception("fail");
        Either<String, Exception> either = new Either<>(null, ex);

        // when / then
        assertThatThrownBy(either::throwError)
                .isInstanceOf(RuntimeException.class)
                .hasCause(ex);
    }

    @Test
    void givenNoError_whenThrowError_thenThrowsIllegalStateException() {
        // given
        Either<String, Exception> either = new Either<>("ok", null);

        // when / then
        assertThatThrownBy(either::throwError)
                .isInstanceOf(IllegalStateException.class);
    }
}
