package Assignment_03;

import java.util.*;

public class PhoneBook {
    private static Map<Long, String> book = new HashMap<>();

    public void add(Long phone, String name) {
        book.put(phone, name);
    }

    public List<Long> get(String name) {
        List<Long> phoneSet = new LinkedList<>();
        if (book.containsValue(name)) {
            for (Map.Entry<Long, String> entry : book.entrySet()) {
                if (entry.getValue().equals(name)) phoneSet.add(entry.getKey());
            }
        }
        return phoneSet;
    }
}
