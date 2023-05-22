package ru.job4j.io.serialization;

import java.util.Arrays;

public class Company {
    private final boolean exist;
    private final int employeesNumber;
    private final String name;
    private final Phone phone;
    private final String[] economicActivities;

    public Company(boolean exist, int employeesNumber, String name, Phone phone, String[] economicActivities) {
        this.exist = exist;
        this.employeesNumber = employeesNumber;
        this.name = name;
        this.phone = phone;
        this.economicActivities = economicActivities;
    }

    @Override
    public String toString() {
        return "Company{"
                + "exist=" + exist
                + ", employeeCount=" + employeesNumber
                + ", name=" + name
                + ", phone=" + phone
                + ", statuses=" + Arrays.toString(economicActivities)
                + '}';
    }
}
