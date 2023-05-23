package ru.job4j.io.serialization;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {
    @XmlAttribute
    private boolean exist;
    @XmlAttribute
    private int employeesNumber;
    @XmlAttribute
    private String name;
    @XmlElement(name = "contact")
    private Phone phone;
    @XmlElementWrapper(name = "economicActivities")
    @XmlElement(name = "economicActivity")
    private String[] economicActivities;

    public Company() {
    }

    public Company(boolean exist, int employeesNumber, String name, Phone phone, String[] economicActivities) {
        this.exist = exist;
        this.employeesNumber = employeesNumber;
        this.name = name;
        this.phone = phone;
        this.economicActivities = economicActivities;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public String getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public String[] getEconomicActivities() {
        return economicActivities;
    }

    public boolean isExist() {
        return exist;
    }

    @Override
    public String toString() {
        return "Company{"
                + "exist=" + exist
                + ", employeeCount=" + employeesNumber
                + ", name=" + name
                + ", phone=" + phone
                + ", economicActivities=" + Arrays.toString(economicActivities)
                + '}';
    }
}
