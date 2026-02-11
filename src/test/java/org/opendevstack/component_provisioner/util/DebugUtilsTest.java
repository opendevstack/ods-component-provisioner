package org.opendevstack.component_provisioner.util;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DebugUtilsTest {
    @Test
    void json_serializesObjectAndWritesToFile() throws Exception {
        var obj = Map.of("key", "value");
        var tempFile = Files.createTempFile("test", ".json");
        String result = DebugUtils.json.apply(obj, tempFile.toString());

        assertTrue(result.contains("\"key\""));
        assertTrue(Files.exists(tempFile));
        String fileContent = Files.readString(tempFile);
        assertEquals(result, fileContent);

        Files.deleteIfExists(tempFile);
    }

    @Test
    void json_throwsExceptionForInvalidPath() {
        var obj = Map.of("key", "value");
        String invalidPath = "/invalid/path/file.json";
        assertThrows(Exception.class, () -> DebugUtils.json.apply(obj, invalidPath));
    }
}
