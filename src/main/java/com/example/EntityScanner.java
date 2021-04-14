package com.example;

import com.example.annotations.Entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class EntityScanner {

    public static Stream<? extends Class<?>> getEntityClasses() throws IOException {
        Path p = Path.of(".");
        return Files.walk(p).map(Path::toString).filter(i -> i.endsWith(".class"))
                .map(i -> i.replace("\\", ".").replace("..target.classes.", "")
                        .replace(".class", ""))
                .filter(i -> i.startsWith("com.example"))
                .map(i -> {
                    try {
                        return Class.forName(i);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(i -> i.getAnnotation(Entity.class) != null);
    }
}
