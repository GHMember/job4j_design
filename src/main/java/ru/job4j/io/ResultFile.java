package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            out.write("Hello, world!".getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileOutputStream multipleOut = new FileOutputStream("data/multiplicationtable.txt")) {
            multipleOut.write(("2 * 2 = " + 2 * 2).getBytes());
            multipleOut.write(System.lineSeparator().getBytes());
            multipleOut.write(("5 * 5 = " + 5 * 5).getBytes());
            multipleOut.write(System.lineSeparator().getBytes());
            multipleOut.write(("10 * 10 = " + 10 * 10).getBytes());
            multipleOut.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
