package ru.job4j.io.finaltest;

import ru.job4j.io.ArgsName;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class CriterionSearch {
    public static void main(String[] args) throws IOException {
        String ls = System.lineSeparator();
        System.out.println(
                "Программа позволяет искать данные в заданном каталоге и подкаталогах." + ls
                + "Результаты поиска записываются в файл" + ls
                + "Перед запуском программы необходимо обязательно заполнить 4 параметра:" + ls
                + "-d - директория, в которой начинать поиск;" + ls
                + "-n - имя файла, маска, либо регулярное выражение;" + ls
                + "-t - тип поиска:" + ls
                        + "\tmask - по маске," + ls
                        + "\tname - по полному совпадение имени," + ls
                        + "\tregex - по регулярному выражению;" + ls
                + "-o - файл, в который записываются результаты поиска." + ls
                + "Имя файла может задаваться, целиком, по маске, по регулярному выражению."
                + "Пример указания параметров поиска и записи: -d=c: -n=*.?xt -t=mask -o=log.txt."
        );
        System.out.print("Чтобы продолжить нажмите Enter");
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments. Expected 4.");
        }
        validation(argsName);
        Path start = Paths.get(argsName.get("d"));
        String pattern = argsName.get("n");
        if (argsName.get("t").equals("mask")) {
           pattern = argsName.get("n").replace("*", "\\w*").replace(".", "[.]").
                    replace("?", "\\w{1}");
        }
        if (argsName.get("t").equals("name")) {
            pattern = "^" + argsName.get("n").replace(".", "[.]") + "$";
        }
        String finalPattern = pattern;
        Path log = Paths.get(argsName.get("o"));
        try (FileWriter fv = new FileWriter(log.toFile())) {
            search(start, p -> p.toFile().getName().matches(finalPattern)).forEach(p -> {
                try {
                    fv.write(p.toString() + System.lineSeparator());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        System.out.printf("Результаты поиска можно посмотреть в файле %s", log.toAbsolutePath());
    }

    private static void validation(ArgsName argsValue) {
        File searchDir = new File(argsValue.get("d"));
        if (!searchDir.exists()) {
            throw new IllegalArgumentException(String.format("Directory doesn't exist %s", searchDir.getAbsoluteFile()));
        }
        if (!searchDir.isDirectory()) {
            throw new IllegalArgumentException(String.format("Path isn't directory %s", searchDir.getAbsoluteFile()));
        }
        if (searchDir.toString() == null) {
            throw new IllegalArgumentException("Missing argument to \"-d\"");
        }
        if (argsValue.get("n") == null) {
            throw new IllegalArgumentException("Missing argument to \"-n\"");
        }
        List<String> types = List.of("mask", "name", "regex");
        if (!types.contains(argsValue.get("t"))) {
            throw new IllegalArgumentException("Search type must be \"mask\" or \"name\" or \"regex\"");
        }
        if (argsValue.get("t") == null) {
            throw new IllegalArgumentException("Missing argument to \"-t\"");
        }
        File log = new File(argsValue.get("o"));
        if (!log.exists()) {
            throw new IllegalArgumentException(String.format("Log file doesn't exist %s", log.getAbsoluteFile()));
        }
        if (!log.isFile()) {
            throw new IllegalArgumentException(String.format("Log isn't file %s", log.getAbsoluteFile()));
        }
        if (log.toString() == null) {
            throw new IllegalArgumentException("Missing argument to \"-o\"");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        CriterionSearch.SearchFiles searcher = new CriterionSearch.SearchFiles(condition);
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
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
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
