package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {

        List<List<String>> list = new ArrayList<>();
        List<String> filterArr = List.of(argsName.get("filter").split(","));
        List<Integer> intList = new ArrayList<>();
        List<StringJoiner> joinerList = new ArrayList<>();

        try (var scanner = new Scanner(new FileReader(argsName.get("path")))
                .useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                List<String> arr = List.of(scanner.next().split(argsName.get("delimiter")));
                list.add(arr);
            }
        }
        List<String> title = list.get(0);
        for (String s : filterArr) {
            if (title.contains(s)) {
                intList.add(title.indexOf(s));
            }
        }
        for (List<String> ls : list) {
            StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
            for (int i : intList) {
                joiner.add(ls.get(i));
            }
            joinerList.add(joiner);
        }
        File file = new File(argsName.get("out"));
        try (FileWriter fv = new FileWriter(file)) {
            for (StringJoiner sj : joinerList) {
                if ("stdout".equals(argsName.get("out"))) {
                    System.out.println(sj.toString());
                } else {
                    fv.write(sj.toString() + System.lineSeparator());
                }
            }
        }
    }

    private static void validation(ArgsName argsValue) {
        File in = new File(argsValue.get("path"));
        if (!in.exists()) {
            throw new IllegalArgumentException(String.format("Source file not exist %s", in.getAbsoluteFile()));
        }
        if (!in.isFile()) {
            throw new IllegalArgumentException(String.format("Source isn't file %s", in.getAbsoluteFile()));
        }
        File out = new File(argsValue.get("out"));
        if (!"stdout".equals(out.toString()) && !out.exists()) {
            throw new IllegalArgumentException(String.format("Out file not exist %s", out.getAbsoluteFile()));
        }
        if (argsValue.get("delimiter").matches("\\w")) {
            throw new IllegalArgumentException("Wrong delimiter");
        }
        if (!argsValue.get("filter").matches("\\w+(,\\w+)*")) {
            throw new IllegalArgumentException("Wrong filter format");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments. Expected 4.");
        }
        validation(argsName);
        handle(argsName);
    }
}
