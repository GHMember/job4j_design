package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String line;
            List<String> list = new ArrayList<>(2);
            boolean serverOnline = false;
            while ((line = reader.readLine()) != null) {
                if (serverOnline == (line.contains("200") || line.contains("300"))) {
                    String[] temp = line.split(" ", 2);
                    list.add(temp[1]);
                    if (list.size() == 2) {
                        writer.printf("%s;%s;%n", list.get(0), list.get(1));
                        list.clear();
                    }
                    serverOnline = !serverOnline;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/source.log", "data/target.csv");
    }
}
