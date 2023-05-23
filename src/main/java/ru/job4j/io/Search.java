package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(p -> System.out.println(p.toFile().getName()));
    }

    private static void validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong number of arguments. Expected 2.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!args[1].matches(".\\S+")) {
            throw new IllegalArgumentException("Wrong file format");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static class SearchFiles extends SimpleFileVisitor<Path> {
        private final Predicate<Path> condition;
        private final List<Path> paths;

        SearchFiles(Predicate<Path> condition) {
            this.condition = condition;
            this.paths = new ArrayList<>();
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (condition.test(file)) {
                paths.add(file);
            }
            return FileVisitResult.CONTINUE;
        }

        private List<Path> getPaths() {
            return paths;
        }
    }
}