package ru.job4j.kiss;

import java.util.Scanner;

public class Fool {
    private int startAt = 1;

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    public String getResult(int startAt) {
        String result;
        if (startAt % 3 == 0 && startAt % 5 == 0) {
            result = "FizzBuzz";
        } else if (startAt % 3 == 0) {
            result = "Fizz";
        } else if (startAt % 5 == 0) {
            result = "Buzz";
        } else {
            result = String.valueOf(startAt);
        }
        return result;
    }

    public void check(String answer) {
        if (!getResult(getStartAt()).equals(answer)) {
            System.out.println("Ошибка. Начинай снова.");
            setStartAt(0);
        }
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        Fool fool = new Fool();
        var io = new Scanner(System.in);
        while (fool.getStartAt() < 100) {
            System.out.println(fool.getResult(fool.getStartAt()));
            fool.setStartAt(fool.getStartAt() + 1);
            var answer = io.nextLine();
            fool.check(answer);
            fool.setStartAt(fool.getStartAt() + 1);
        }
    }
}
