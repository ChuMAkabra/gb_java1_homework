package Assignment_03;

import java.util.*;

/**
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и
 * телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять записи.
 * С помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией
 * может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны
 * выводиться все телефоны.
 * Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную
 * запись добавлять еще дополнительные поля (имя, отчество, адрес), делать взаимодействие с
 * пользователем через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner),
 * тестировать просто из метода main(), прописывая add() и get().
 */
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
//            for(String value : book.values()) {
//                if (value == name) phoneSet.add();
//            }
        }

        return phoneSet;
    }
}
