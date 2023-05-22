package ru.job4j.io.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
    public static void main(String[] args) {
        final Company company = new Company(false, 0, "MyCompany",
                new Phone("00-00-00"), new String[] {"Education", "Construction"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(company));
        final String companyJson =
                "{"
                        + "\"exist\":true,"
                        + "\"employeesNumber\":10,"
                        + "\"name\":\"MyCompany\","
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"77-77-77\""
                        + "},"
                        + "\"economicActivities\":"
                        + "[\"Education\",\"Construction\"]"
                        + "}";
        final Company companyMod = gson.fromJson(companyJson, Company.class);
        System.out.println(companyMod);
    }
}