package ru.job4j.kiss;

import java.util.Scanner;

public class Fool {
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

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        Fool fool = new Fool();
        int startAt = 1;
        var io = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(fool.getResult(startAt));
            startAt++;
            var answer = io.nextLine();
            if (!fool.getResult(startAt).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}
