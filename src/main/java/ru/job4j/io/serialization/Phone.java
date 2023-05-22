package ru.job4j.io.serialization;

public class Phone {
    private final String phone;

    public Phone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Phone{"
                + "phone='" + phone + '\''
                + '}';
    }
}
