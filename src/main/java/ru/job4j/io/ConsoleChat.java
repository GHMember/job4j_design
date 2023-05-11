package ru.job4j.io;


import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        boolean showAnswer = true;
        String phrase = null;
        List<String> log = new ArrayList<>();
        while (!OUT.equals(phrase)) {
            int randomIndex = new Random().nextInt(readPhrases().size() - 1);
            String answer = readPhrases().get(randomIndex);
            System.out.print("Input a phrase: ");
            phrase = in.next();
            log.add(phrase);
            if (STOP.equals(phrase)) {
                showAnswer = false;
                continue;
            }
            if (CONTINUE.equals(phrase)) {
                showAnswer = true;
                continue;
            }
            if (showAnswer && (!OUT.equals(phrase))) {
                System.out.println(answer);
                log.add(answer);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(phrases::add);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path,
                Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("./data/chatlog.txt", "./data/botanswers.txt");
        cc.run();
    }
}
