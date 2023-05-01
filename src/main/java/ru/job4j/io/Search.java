package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith(".js")).forEach(System.out::println);
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
                paths.add(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }

        private List<Path> getPaths() {
            return paths;
        }
    }
}