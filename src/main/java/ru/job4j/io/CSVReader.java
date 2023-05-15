package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {

        List<String[]> list = new ArrayList<>();
        List<String> filterArr = List.of(argsName.get("filter").split(","));
        List<Integer> intList = new ArrayList<>();
        List<StringJoiner> joinerList = new ArrayList<>();

        try (var scanner = new Scanner(new FileReader(argsName.get("path")))) {
            while (scanner.hasNext()) {
                String[] arr = scanner.next().split(argsName.get("delimiter"));
                list.add(arr);
            }
        }
        for (int i = 0; i < list.get(0).length; i++) {
            if (filterArr.contains(list.get(0)[i])) {
                intList.add(i);
            }
        }
        for (String[] arr : list) {
            StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
            for (int i : intList) {
                joiner.add(arr[i]);
            }
            joinerList.add(joiner);
        }

        File file = new File(argsName.get("out"));
        try (FileWriter fv = new FileWriter(file)) {
            for (StringJoiner sj : joinerList) {
                if (argsName.get("out").equals("stdout")) {
                    System.out.println(sj.toString());
                } else {
                    fv.write(sj.toString() + "\n");
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
        if (!out.toString().equals("stdout") && !out.exists()) {
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
