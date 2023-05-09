package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
       try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
           for (Path source : sources) {
               zip.putNextEntry(new ZipEntry(source.toString()));
               try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                   zip.write(out.readAllBytes());
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validation(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong number of arguments. Expected 3.");
        }
        File file = new File(getArgsValue(args, "d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!args[1].matches(".\\S+")) {
            throw new IllegalArgumentException("Wrong file format");
        }
        if (!args[2].matches("\\S+.zip")) {
            throw new IllegalArgumentException("Wrong archive format");
        }
    }

    private static String getArgsValue(String[] args, String key) {
        ArgsName argsValue = ArgsName.of(args);
        return argsValue.get(key);
    }

    public static void main(String[] args) throws IOException {
        validation(args);
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        Path root = Paths.get(getArgsValue(args, "d"));
        List<Path> sources = new ArrayList<>(Search.search(root, p -> !p.toFile().getName().
                endsWith(getArgsValue(args, "e"))));
        zip.packFiles(sources, new File(getArgsValue(args, "o")));
    }
}
