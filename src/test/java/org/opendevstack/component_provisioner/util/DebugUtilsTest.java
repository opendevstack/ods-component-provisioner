package org.opendevstack.component_provisioner.util;

import java.nio.file.Files;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DebugUtilsTest {

    @Test
    void givenObjectAndFilePath_whenJsonApplied_thenSerializesAndWritesToFile() throws Exception {
        // given
        var obj = Map.of("key", "value");
        var tempFile = Files.createTempFile("test", ".json");

        // when
        var result = DebugUtils.json.apply(obj, tempFile.toString());

        // then
        assertThat(result).contains("\"key\"");
        assertThat(Files.exists(tempFile)).isTrue();
        assertThat(Files.readString(tempFile)).isEqualTo(result);

        Files.deleteIfExists(tempFile);
    }

    @Test
    void givenInvalidFilePath_whenJsonApplied_thenThrowsException() {
        // given
        var obj = Map.of("key", "value");
        var invalidPath = "/invalid/path/file.json";

        // when / then
        assertThatThrownBy(() -> DebugUtils.json.apply(obj, invalidPath))
                .isInstanceOf(Exception.class);
    }
}
