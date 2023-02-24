package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void validateNotContainsEqualSign() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key-value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key-value does not contain the symbol \"=\"");
    }

    @Test
    void validateNotContainsKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=keyvalue"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=keyvalue does not contain a key");
    }

    @Test
    void validateNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("keyvalue="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("keyvalue= does not contain a value");
    }

}