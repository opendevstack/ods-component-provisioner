package com.boehringer.componentprovisioner.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.BiFunction;

import static com.boehringer.componentprovisioner.util.EitherUtils.uncheckedFrom;

public class DebugUtils {

    private DebugUtils() {
        // Hide the implicit public constructor
    }

    public static final BiFunction<Object, Object, String> json = uncheckedFrom((obj, file) -> {
        var j = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(obj);
        Files.writeString(Paths.get((String) file), j);
        return j;
    });
}
