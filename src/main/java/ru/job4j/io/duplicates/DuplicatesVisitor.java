package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, Path> map;
    private final Set<Path> set;

    DuplicatesVisitor() {
        this.map = new HashMap<>();
        this.set = new HashSet<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().isFile()) {
            FileProperty fp = new FileProperty(file.toFile().length(), file.getFileName().toString());
            Path path = map.get(fp);
            if (map.put(fp, file.toAbsolutePath()) != null) {
                set.add(map.get(fp));
                if (set.add(path)) {
                    System.out.format("%s - %d%n", fp.getName(), fp.getSize());
                    System.out.println(path);
                }
                System.out.println(map.get(fp));
            }
        }
        return super.visitFile(file, attrs);
    }
}

