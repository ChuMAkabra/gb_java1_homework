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
        String[][] strArray0 = {{"1", "0", "5", "9"},
                                {"2", "0", "6", "10"},
                                {"3", "0", "7", "0"},
                                {"4", "0", "8", "0"}
        };
        String[][] strArray1 = {{"1", "0", "5", "9"},
                                {"2", "0", "6", "10"},
//                                {"3", "0", "7", "0"}, // испортили размерность
                                {"4", "0", "8", "e"}    // тут тоже ошибка, но до нее не дойдем
        };
        String[][] strArray2 = {{"1", "0", "5", "9"},
                                {"2", "0", "6", "10"},
                                {"3", "0", "7", "0"},
                                {"4", "0", "8", "e"}    // в последнем элементе ошибка
        };

/**
 * Астамур, подскажите, пожалуйста:
 * 1) есть ли более красивый способ совершить все вызовы, а не только один?
 * 2) можно ли как-то продолжить выполнение блока try после обработки исключения? (например,
 * пропустить выполнение ошибочного кода и перейти к следующей строчке?)
 */
        String[][][] arrays = new String[][][] {strArray0, strArray1, strArray2};
            for (String[][] strArray : arrays) {
            try {
                System.out.println(arraySum(strArray));
            } catch (MyArrayDataException | MyArraySizeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int arraySum(String[][] array) throws MyArraySizeException, MyArrayDataException{
        int numRows = array.length;
        for (String[] strings : array) {
            if (numRows != 4 || strings.length != 4)
                throw new MyArraySizeException("Размерность массива должна быть 4х4");
        }
        int sum = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch(NumberFormatException e) {
                    throw new MyArrayDataException("Ячейка [" + i + ", " + j + "] содержит " +
                            "некорректное значение", e);
                }
            }
        }
        return sum;
    }

    public static class MyArraySizeException extends RuntimeException {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    public static class MyArrayDataException extends RuntimeException {
        public MyArrayDataException(String message, Throwable cause) {
            super(message, cause);
        }

        @Override
        public String getMessage() {
            return super.getMessage() + ": " + getCause().getMessage();
        }
    }
}