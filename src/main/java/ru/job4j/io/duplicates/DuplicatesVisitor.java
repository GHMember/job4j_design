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
    private List<String> result;

    DuplicatesVisitor() {
        this.map = new HashMap<>();
        this.set = new HashSet<>();
        this.result = new ArrayList<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().isFile()) {
            FileProperty fp = new FileProperty(file.toFile().length(), file.getFileName().toString());
            Path path = map.get(fp);
            if (map.put(fp, file.toAbsolutePath()) != null) {
                set.add(map.get(fp));
                if (set.add(path)) {

                    result.add(fp.getName() + " - " + fp.getSize() + "%n");
                    result.add(path.toString());
                }
                result.add(map.get(fp).toString());
            }
        }
        return super.visitFile(file, attrs);
    }

    public void getMessage() {
        for (String str : result) {
            System.out.println(str);
        }
    }
}

