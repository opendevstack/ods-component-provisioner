package org.opendevstack.component_provisioner.server.services.common;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.server.services.exceptions.InvalidIdException;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.opendevstack.component_provisioner.server.services.common.IdEncoderDecoder.*;

class IdEncoderDecoderTest {

    @Test
    void givenAString_whenIdEncodeIsCalled_thenReturnsBase64EncodedString() {
        // given
        String input = "test-string";

        // when
        String result = idEncode(input);

        // then
        assertThat(result).isEqualTo(Base64.getUrlEncoder().encodeToString(input.getBytes()));
    }

    @Test
    void givenANullString_whenNullableIdEncodeIsCalled_thenReturnsNull() {
        // given
        String input = null;

        // when
        String result = nullableIdEncode(input);

        // then
        assertThat(result).isNull();
    }

    @Test
    void givenAString_whenNullableIdEncodeIsCalled_thenReturnsEncodedString() {
        // given
        String input = "test";

        // when
        String result = nullableIdEncode(input);

        // then
        assertThat(result).isEqualTo(idEncode(input));
    }

    @Test
    void givenAnEncodedString_whenIdDecodeIsCalled_thenReturnsDecodedString() throws InvalidIdException {
        // given
        String input = "dGVzdC1zdHJpbmc";

        // when
        String result = idDecode(input);

        // then
        assertThat(result).isEqualTo("test-string");
    }

    @Test
    void givenAnInvalidEncodedString_whenIdDecodeIsCalled_thenThrowsInvalidIdException() {
        // given
        String input = "!!!not-base64!!!";

        // when / then
        assertThatThrownBy(() -> idDecode(input))
                .isInstanceOf(InvalidIdException.class);
    }

    @Test
    void givenANullString_whenNullableIdDecodeIsCalled_thenReturnsNull() throws InvalidIdException {
        // given
        String input = null;

        // when
        String result = nullableIdDecode(input);

        // then
        assertThat(result).isNull();
    }

    @Test
    void givenAnEncodedString_whenNullableIdDecodeIsCalled_thenReturnsDecodedString() throws InvalidIdException {
        // given
        String input = "dGVzdA";

        // when
        String result = nullableIdDecode(input);

        // then
        assertThat(result).isEqualTo("test");
    }

    @Test
    void givenPrivateConstructor_whenAttemptingToInstantiate_thenThrowsNoSuchMethodException() throws Exception {
        // given
        java.lang.reflect.Constructor<IdEncoderDecoder> constructor = IdEncoderDecoder.class.getDeclaredConstructor();
        assertThat(java.lang.reflect.Modifier.isPrivate(constructor.getModifiers())).isTrue();

        // when
        constructor.setAccessible(true);
        IdEncoderDecoder instance = constructor.newInstance();

        // then
        assertThat(instance).isNotNull();
    }
}
