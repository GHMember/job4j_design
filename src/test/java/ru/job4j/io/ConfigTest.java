package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("key1")).isEqualTo("value1=1");
        assertThat(config.value("key2")).isEqualTo("value2=");
    }

    @Test
    void whenPairWithComment() {
        String path = "data/app_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("key1")).isEqualTo("value1=1");
        assertThat(config.value("key2")).isEqualTo("value2=");
    }

    @Test
    void whenPairWithBlankLines() {
        String path = "data/app_with_blank_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("key1")).isEqualTo("value1=1");
        assertThat(config.value("key2")).isEqualTo("value2=");
    }

    @Test
    void whenPairStartWithEqualSign() {
        String path = "data/start_with_=.txt";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=org.postgresql.Driver");
    }

    @Test
    void whenPairEndWithEqualSign() {
        String path = "data/end_with_=.txt";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("org.postgresql.Driver=");
    }

    @Test
    void whenPairHasOnlyEqualSign() {
        String path = "data/only_=.txt";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(" =");
    }
}