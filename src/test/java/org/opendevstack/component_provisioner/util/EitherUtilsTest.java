package org.opendevstack.component_provisioner.util;

import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EitherUtilsTest {

    @Test
    void givenThrowingFunction_whenUncheckedFromApplied_thenReturnsValue() {
        // given
        Function<Integer, Integer> fun = EitherUtils.uncheckedFrom((Integer x) -> x + 1);

        // when
        var result = fun.apply(1);

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void givenThrowingFunctionThatThrows_whenUncheckedFromApplied_thenThrowsRuntimeException() {
        // given
        Function<Integer, Integer> fun = EitherUtils.uncheckedFrom((Integer x) -> {
            if (x < 0) throw new Exception("Negative");
            return x;
        });

        // when / then
        assertThatThrownBy(() -> fun.apply(-1))
                .isInstanceOf(RuntimeException.class)
                .hasCauseInstanceOf(Exception.class)
                .cause().hasMessage("Negative");
    }

    @Test
    void givenValue_whenEitherOf_thenContainsValueAndNoError() {
        // given / when
        Either<Integer, Exception> either = EitherUtils.eitherOf(5);

        // then
        assertThat(either.getValue()).isEqualTo(5);
        assertThat(either.getError()).isNull();
    }

    @Test
    void givenException_whenEitherOf_thenContainsErrorAndNoValue() {
        // given
        var e = new Exception("error");

        // when
        Either<Integer, Exception> either = EitherUtils.eitherOf(e);

        // then
        assertThat(either.getValue()).isNull();
        assertThat(either.getError()).isEqualTo(e);
    }

    @Test
    void givenFunction_whenEitherFromApplied_thenReturnsEitherWithValue() {
        // given
        Function<Integer, Either<Integer, ? extends Exception>> fun = EitherUtils.eitherFrom(x -> x + 2);

        // when
        Either<Integer, ? extends Exception> either = fun.apply(3);

        // then
        assertThat(either.getValue()).isEqualTo(5);
        assertThat(either.getError()).isNull();
    }

    @Test
    void givenFunctionThatThrows_whenEitherFromApplied_thenReturnsEitherWithError() {
        // given
        Function<Integer, Either<Integer, ? extends Exception>> fun = EitherUtils.eitherFrom(x -> {
            if (x < 0) throw new RuntimeException("fail");
            return x;
        });

        // when
        Either<Integer, ? extends Exception> either = fun.apply(-1);

        // then
        assertThat(either.getValue()).isNull();
        assertThat(either.getError()).isNotNull();
    }

    @Test
    void givenFunction_whenMaybeValueFromApplied_thenReturnsOptionalWithValue() {
        // given
        Function<Integer, Optional<Integer>> fun = EitherUtils.maybeValueFrom(x -> x * 2);

        // when
        Optional<Integer> result = fun.apply(2);

        // then
        assertThat(result).isEqualTo(Optional.of(4));
    }

    @Test
    void givenFunctionThatThrows_whenMaybeValueFromApplied_thenReturnsEmptyOptional() {
        // given
        Function<Integer, Optional<Integer>> fun = EitherUtils.maybeValueFrom(x -> {
            if (x < 0) throw new RuntimeException();
            return x;
        });

        // when
        Optional<Integer> result = fun.apply(-1);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenSuccessfulFunction_whenMaybeErrorFromApplied_thenReturnsEmptyOptional() {
        // given
        Function<Integer, Optional<? extends Exception>> fun = EitherUtils.maybeErrorFrom(x -> x + 1);

        // when
        Optional<? extends Exception> result = fun.apply(1);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenFunctionThatThrows_whenMaybeErrorFromApplied_thenReturnsOptionalWithError() {
        // given
        Function<Integer, Optional<? extends Exception>> fun = EitherUtils.maybeErrorFrom(x -> {
            if (x < 0) throw new RuntimeException("bad");
            return x;
        });

        // when
        Optional<? extends Exception> error = fun.apply(-1);

        // then
        assertThat(error).isPresent();
        assertThat(error.get().getMessage()).isEqualTo("bad");
    }
}