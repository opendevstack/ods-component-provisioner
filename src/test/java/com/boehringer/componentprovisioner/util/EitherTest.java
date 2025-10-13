package com.boehringer.componentprovisioner.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EitherTest {
    @Test
    void constructor_setsValueAndErrorCorrectly_whenNoError() {
        Either<String, Exception> either = new Either<>("ok", null);
        assertEquals("ok", either.getValue());
        assertNull(either.error);
        assertTrue(either.ok);
        assertFalse(either.failed);
    }

    @Test
    void constructor_setsErrorAndFlagsCorrectly_whenErrorPresent() {
        Exception ex = new Exception("fail");
        Either<String, Exception> either = new Either<>(null, ex);
        assertNull(either.getValue());
        assertEquals(ex, either.error);
        assertFalse(either.ok);
        assertTrue(either.failed);
    }

    @Test
    void throwError_throwsRuntimeException_whenErrorPresent() {
        Exception ex = new Exception("fail");
        Either<String, Exception> either = new Either<>(null, ex);
        RuntimeException thrown = assertThrows(RuntimeException.class, either::throwError);
        assertEquals(ex, thrown.getCause());
    }

    @Test
    void throwError_throwsIllegalStateException_whenNoError() {
        Either<String, Exception> either = new Either<>("ok", null);
        assertThrows(IllegalStateException.class, either::throwError);
    }
}
