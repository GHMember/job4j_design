package ru.job4j.set;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenContainsIsFalse() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        assertThat(set.contains(4)).isFalse();
    }

    @Test
    void whenAddSuccess() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        assertThat(set).hasSize(2);
    }

    @Test
    void whenAddFail() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        assertThat(set).hasSize(1);
    }

}