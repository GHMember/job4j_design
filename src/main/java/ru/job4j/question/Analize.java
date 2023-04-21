package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info result = new Info(0, 0, 0);
        int added = 0;
        int changed = 0;
        int deleted = 0;

        Map<Integer, String> prev = new HashMap<>();
        Map<Integer, String> curr = new HashMap<>();
        for (User user : previous) {
            prev.put(user.getId(), user.getName());
        }
        for (User user : current) {
            curr.put(user.getId(), user.getName());
        }
        for (Map.Entry<Integer, String> pair : curr.entrySet()) {
            if (!prev.containsKey(pair.getKey())) {
                added++;
                result.setAdded(added++);
            }
            if (prev.containsKey(pair.getKey()) && !(pair.getValue().equals(prev.get(pair.getKey())))) {
                changed++;
                result.setChanged(changed);
            }
        }
        for (Map.Entry<Integer, String> pair : prev.entrySet()) {
            if (!curr.containsKey(pair.getKey())) {
                deleted++;
                result.setDeleted(deleted);
            }
        }
        return result;
    }
}