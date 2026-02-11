package org.opendevstack.component_provisioner.util;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class EitherUtilsTest {

    @Test
    void testUncheckedFromThrowingFunctionReturnsValue() {
        Function<Integer, Integer> fun = EitherUtils.uncheckedFrom((Integer x) -> x + 1);
        assertEquals(2, fun.apply(1));
    }

    @Test
    void testUncheckedFromThrowingFunctionThrowsRuntimeException() {
        Function<Integer, Integer> fun = EitherUtils.uncheckedFrom((Integer x) -> {
            if (x < 0) throw new Exception("Negative");
            return x;
        });
        RuntimeException ex = assertThrows(RuntimeException.class, () -> fun.apply(-1));
        assertEquals("Negative", ex.getCause().getMessage());
    }

    @Test
    void testEitherOfValue() {
        Either<Integer, Exception> either = EitherUtils.eitherOf(5);
        assertEquals(5, either.getValue());
        assertNull(either.getError());
    }

    @Test
    void testEitherOfError() {
        Exception e = new Exception("error");
        Either<Integer, Exception> either = EitherUtils.eitherOf(e);
        assertNull(either.getValue());
        assertEquals(e, either.getError());
    }

    @Test
    void testEitherFromFunctionReturnsValue() {
        Function<Integer, Either<Integer, ? extends Exception>> fun = EitherUtils.eitherFrom(x -> x + 2);
        Either<Integer, ? extends Exception> either = fun.apply(3);
        assertEquals(5, either.getValue());
        assertNull(either.getError());
    }

    @Test
    void testEitherFromFunctionReturnsError() {
        Function<Integer, Either<Integer, ? extends Exception>> fun = EitherUtils.eitherFrom(x -> {
            if (x < 0) throw new RuntimeException("fail");
            return x;
        });
        Either<Integer, ? extends Exception> either = fun.apply(-1);
        assertNull(either.getValue());
        assertNotNull(either.getError());
    }

    @Test
    void testMaybeValueFromFunctionReturnsValue() {
        Function<Integer, Optional<Integer>> fun = EitherUtils.maybeValueFrom(x -> x * 2);
        assertEquals(Optional.of(4), fun.apply(2));
    }

    @Test
    void testMaybeValueFromFunctionReturnsEmptyOnError() {
        Function<Integer, Optional<Integer>> fun = EitherUtils.maybeValueFrom(x -> {
            if (x < 0) throw new RuntimeException();
            return x;
        });
        assertEquals(Optional.empty(), fun.apply(-1));
    }

    @Test
    void testMaybeErrorFromFunctionReturnsEmptyOnSuccess() {
        Function<Integer, Optional<? extends Exception>> fun = EitherUtils.maybeErrorFrom(x -> x + 1);
        assertTrue(fun.apply(1).isEmpty());
    }

    @Test
    void testMaybeErrorFromFunctionReturnsError() {
        Function<Integer, Optional<? extends Exception>> fun = EitherUtils.maybeErrorFrom(x -> {
            if (x < 0) throw new RuntimeException("bad");
            return x;
        });
        Optional<? extends Exception> error = fun.apply(-1);
        assertTrue(error.isPresent());
        assertEquals("bad", error.get().getMessage());
    }
}