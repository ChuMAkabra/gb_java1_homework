package Assignment_03;

import java.util.*;

/**
 * 1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать, сколько раз встречается каждое слово.
 *
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

public class Main {
    public static void main(String[] args) {
        countWords();
        checkPhoneBook();
    }

    private static void countWords() {
        String[] words = {"bind", "bound", "bound", "be", "was", "were", "been",
                          "beat", "beat", "beaten", "begin", "began", "begun",
                          "become", "became", "become", "bear", "bore", "born"};

        System.out.printf("Исходный массив: %s\n", Arrays.toString(words));

        TreeSet<String> wordSet = new TreeSet<>(Arrays.asList(words));
        System.out.printf("Вывод сортированных уникальных слов через TreeSet: %s\n", wordSet);

        HashMap<String, Integer> wordHashMap = new HashMap<>(words.length);
        TreeMap<String, Integer> wordTreeMap = new TreeMap<>();
        populateMap(words, wordTreeMap);
        populateMap(words, wordHashMap);

        System.out.printf("Вывод подсчета в виде TreeMap: %s\n", wordTreeMap);
        System.out.printf("Вывод подсчета в виде HashMap: %s\n", wordHashMap);
    }

    private static void checkPhoneBook() {
        PhoneBook ph = new PhoneBook();
        ph.add(89151000000L, "Петров");
        ph.add(89151234567L, "Сидоров");
        ph.add(89151222222L, "Иванов");
        ph.add(89151333333L, "Иванов");
        ph.add(89151444444L, "Иванов");
        System.out.println(ph.get("Иванов"));
        System.out.println(ph.get("Сидоров"));
        System.out.println(ph.get("Петров"));
    }

    private static void populateMap(String[] words, Map<String, Integer> wordTreeMap) {
        for (String word : words) {
            if (!wordTreeMap.containsKey(word)) wordTreeMap.put(word, 1);
            else wordTreeMap.put(word, wordTreeMap.get(word) + 1);
        }
    }
}
