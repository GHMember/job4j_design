package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveByFilter() {
        input = new ArrayList<>(Arrays.asList(3, 1, 1, 2, 1, 4));
        Predicate<Integer> filter = s -> s > 1;
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(3).containsSequence(1, 1, 1);
    }

    @Test
    void whenReplaceByFilter() {
        input = new ArrayList<>(Arrays.asList(2, 3, 4, 3, 2));
        Predicate<Integer> filter = s -> s != 3;
        ListUtils.replaceIf(input, filter, 1);
        assertThat(input).containsSequence(1, 3, 1, 3, 1);
    }

    @Test
    void whenRemoveAllFromOtherList() {
        input = new ArrayList<>(Arrays.asList(3, 2, 4, 2, 3));
        List<Integer> delList = List.of(2, 3, 5);
        ListUtils.removeAll(input, delList);
        assertThat(input).hasSize(1).containsSequence(4);
    }
}