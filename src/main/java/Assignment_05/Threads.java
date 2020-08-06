package Assignment_05;
import java.util.ArrayList;
import java.util.Arrays;

public class Threads {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr1;
    static float[] arr2;

    public static void main(String[] args) {
        // создаем одномерный длинный массив
        arr1 = new float[size];
        // заполняем этот массив единицами
        Arrays.fill(arr1, 1.0F);
        arr2 = Arrays.copyOf(arr1, size);

        // первый просто бежит по массиву и вычисляет значения
        firstMethod();
        // второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом
        // склеивает эти массивы обратно в один.
        secondMethod();
        compareArrays(Threads.arr1, arr2);
    }

    private static void compareArrays(float[] arr1, float[] arr2) {
        for (int i = 0; i < size; i++) {
            if (arr1[i] != arr2[i]) {
                System.out.printf("элементы с индексом %d не совпадают\n", i);
                break;
            }
        }
    }

    private static void firstMethod() {
        // засекаем время выполнения
        long a = System.currentTimeMillis();
        // выполняем вычисления
        calculate(arr1);
        // выводим в консоль время работы
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void calculate(float[] arr) {
        // проходим по всему массиву, и для каждой ячейки считаем новое значение по формуле:
        int sz = arr.length;
        for (int i = 0; i < sz; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0)
                    * Math.cos(0.4f + i / 2.0));
        }
    }

    private static void secondMethod() {
        // засекаем время выполнения
        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        // разбиваем массив на два
        divideArray(arr2, a1, a2);
        // выполняем вычисления в двух потоках одновременно
        new Thread(() -> calculate(a1)).start();
        new Thread(() -> calculate(a2)).start();
        // склеиваем массив
        arr2 = mergeArrays(a1, a2);
        // выводим в консоль время работы:
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void divideArray(float[] arr, float[] a1, float[] a2) {
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
    }

    private static float[] mergeArrays(float[] a1, float[] a2) {
        float[] arr = new float[size];
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        return arr;
    }
}