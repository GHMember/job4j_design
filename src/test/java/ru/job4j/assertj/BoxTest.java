package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere").isNotNull();
    }
    @Test
    void NumberOfVerticesIsLessThanZeroAndIsGreaterThanMinusTwo() {
        Box box = new Box(2, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isLessThan(0).
                isGreaterThan(-2);
    }
    @Test
    void DoseBoxExist() {
        Box box = new Box(0, 10);
        boolean existence = box.isExist();
        assertThat(existence).isTrue().isNotEqualTo(false);
    }
    @Test
    void Area() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(1256.6d, withPrecision(0.1d)).isGreaterThan(1256.6d);
    }
}