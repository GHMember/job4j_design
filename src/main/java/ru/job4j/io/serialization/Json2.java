package ru.job4j.io.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

public class Json2 {

    public static void main(String[] args) {
        final Company company = new Company(true, 10, "MyCompany",
                new Phone("77-77-77"), new String[] {"Education", "Construction"});
        JSONObject jsonContact = new JSONObject(company.getPhone());
        JSONArray jsonActivities = new JSONArray(company.getEconomicActivities());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("exist", company.isExist());
        jsonObject.put("employeesNumber", company.getEmployeesNumber());
        jsonObject.put("name", company.getName());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("activities", jsonActivities);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(company));
    }
}
