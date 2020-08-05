package Assignment_05;


import java.util.Arrays;

public class Threads {
    // 1) Создают одномерный длинный массив, например:
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        float[] arr = new float[size];
        //2) Заполняют этот массив единицами;
        Arrays.fill(arr, 1.0F);
        //3) Засекают время выполнения:
        // Первый просто бежит по массиву и вычисляет значения.
        firstMethod(arr);
        // Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
        secondMethod(arr);
    }

    private static void firstMethod(float[] arr) {
        long a = System.currentTimeMillis();
        calculate(arr);
        //5) Проверяется время окончания метода System.currentTimeMillis();
        //6) В консоль выводится время работы:
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void secondMethod(float[] arr) {
        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        divideArray(arr, a1, a2);
        new Thread(() -> calculate(a1)).start();
        new Thread(() -> calculate(a2)).start();
        float[] arrNew = mergeArray(a1, a2);
        //6) В консоль выводится время работы:
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void calculate(float[] arr) {
        //4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
        int sz = arr.length;
        for (int i = 0; i < sz; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0)
                    * Math.cos(0.4f + i / 2.0));
        }
    }

    //    Пример обратной склейки:
    private static void divideArray(float[] arr, float[] a1, float[] a2) {
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
    }

    private static float[] mergeArray(float[] a1, float[] a2) {
        float[] arr = new float[size];
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        return arr;
    }
}
