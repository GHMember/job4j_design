package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("User", 1, calendar);
        User user2 = new User("User", 1, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.forEach((k, v)->System.out.printf("Ключ: %s  Значение: %s \n", k, v));
        int hash1 = user1.hashCode() ^ (user1.hashCode() >>> 16);
        int hash2 = user2.hashCode() ^ (user2.hashCode() >>> 16);
        int bucket1 = hash1 & 15;
        int bucket2 = hash2 & 15;
        System.out.printf("user1 - HashCode: %s  Hash: %s Bucket: %s \n", user1.hashCode() , hash1, bucket1);
        System.out.printf("user2 - HashCode: %s  Hash: %s Bucket: %s \n", user2.hashCode() , hash2, bucket2);
    }
}
