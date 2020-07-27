package Assignment_02;

/**
 * 1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4, при подаче
 * массива другого размера необходимо бросить исключение MyArraySizeException.
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
 * Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или
 * текст вместо числа), должно быть брошено исключение MyArrayDataException – с детализацией, в
 * какой именно ячейке лежат неверные данные.
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException
 * и MyArrayDataException и вывести результат расчета.
 */

public class ArrayExceptions {
    public static void main(String[] args) {
        String[][] strArray = { {"1", "no", "5", "9" },
                                {"2", "a",  "6", "10"},
//                                {"3", "b",  "7", "d" },
                                {"4", "c",  "8", "e" }
        };
        System.out.println(arraySum(strArray));
    }

    public static int arraySum(String[][] array) {
        try {
            if (array.length != 4 || array[0].length != 4)
                throw new MyArraySizeException("АААААААА");
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static class MyArraySizeException extends RuntimeException {
        public MyArraySizeException(String message) {
            super(message);
        }
    }
}