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
        calculate(arr,5, 2);
    }

    private static void firstMethod(float[] arr) {
        long a = System.currentTimeMillis();
        calculate(arr, 5.0, 2.0);
        //5) Проверяется время окончания метода System.currentTimeMillis();
        //6) В консоль выводится время работы:
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void secondMethod(float[] arr) {
        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        divideArray(arr, a1, a2);
        calculate(a1, 5.0, 2.0);
        calculate(a2, 5.0, 2.0);
        float[] arrNew = mergeArray(a1, a2);
        //6) В консоль выводится время работы:
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void calculate(float[] arr, double v, double v2) {
        //4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / v) * Math.cos(0.2f + i / v)
                    * Math.cos(0.4f + i / v2));
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
