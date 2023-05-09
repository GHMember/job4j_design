package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> sources, File target) {
       try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
           for (File source : sources) {
               zip.putNextEntry(new ZipEntry(source.getPath()));
               try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
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
        List<File> sources = new ArrayList<>();
        Path root = Paths.get(".");
        Search.search(root, p -> !p.toFile().getName().endsWith(getArgsValue(args, "e"))).
                forEach(p -> sources.add(p.toFile()));
        zip.packFiles(sources, new File(getArgsValue(args, "o")));
    }
}