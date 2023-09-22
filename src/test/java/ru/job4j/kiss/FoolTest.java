package ru.job4j.kiss;

import org.junit.jupiter.api.Test;
<<<<<<< HEAD

=======
>>>>>>> dbfce43 (Правки к заданию 2. Принципы Kiss, Dry и Yagni [#238813])
import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void whenNumberIsMultiplesOfThreeAndMultiplesOfFive() {
        Fool fool = new Fool();
        int startAt = 15;
        String result = fool.getResult(startAt);
        assertThat(result).isNotEqualTo("15");
        assertThat(result).isEqualTo("FizzBuzz");
    }

    @Test
    void whenNumberIsMultiplesOfThree() {
        Fool fool = new Fool();
        int startAt = 6;
        String result = fool.getResult(startAt);
        assertThat(result).isNotEqualTo("6");
        assertThat(result).isEqualTo("Fizz");
    }

    @Test
    void whenNumberIsMultiplesOfFive() {
        Fool fool = new Fool();
        int startAt = 10;
        String result = fool.getResult(startAt);
        assertThat(result).isNotEqualTo("10");
        assertThat(result).isEqualTo("Buzz");
    }

    @Test
    void whenWrongUserAnswerThenGameRestart() {
        Fool fool = new Fool();
        String userAnswer = "3";
        fool.check(userAnswer);
        assertThat(fool.getStartAt()).isNotEqualTo(3);
        assertThat(fool.getStartAt()).isEqualTo(0);
    }
<<<<<<< HEAD


=======
>>>>>>> dbfce43 (Правки к заданию 2. Принципы Kiss, Dry и Yagni [#238813])
}