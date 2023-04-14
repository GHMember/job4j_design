package question;

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
        }
        for (Map.Entry<Integer, String> pair1 : prev.entrySet()) {
            if (!curr.containsKey(pair1.getKey())) {
                deleted++;
                result.setDeleted(deleted);
            }
            for (Map.Entry<Integer, String> pair2 : curr.entrySet()) {
                if (pair1.getKey().equals(pair2.getKey())
                        && !(pair1.getValue().equals(pair2.getValue()))) {
                    changed++;
                    result.setChanged(changed);
                }
            }
        }
        return result;
    }
}