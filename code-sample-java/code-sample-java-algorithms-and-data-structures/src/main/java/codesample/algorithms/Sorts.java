package codesample.algorithms;


import java.util.List;

public class Sorts {

    private Sorts() {
        // no creation
    }

    /**
     * Sort array using selection sort
     *
     * @param array input array.
     * @param left  left index of array. Included in sort
     * @param right right index of array. NOT INCLUDED in sort
     */
    public static <T extends Comparable<T>> void selectionSort(List<T> array, int left, int right) {
        for (int i = left; i < right; ++i) {
            int minIndex = i;

            for (int j = i; j < right; ++j) {
                if (less(array.get(j), array.get(minIndex))) {
                    minIndex = j;
                }
            }
            swap(array, minIndex, i);
        }
    }

    /**
     * Sort array using insertion sort (aka bubble sort)
     *
     * @param array input array.
     * @param left  left index of array. Included in sort
     * @param right right index of array. NOT INCLUDED in sort
     */
    public static <T extends Comparable<T>> void insertionSort(List<T> array, int left, int right) {
        for (int i = left; i < right; ++i) {
            for (int j = left + 1; j < right - i; ++j) {
                if (less(array.get(j), array.get(j - 1))) swap(array, j - 1, j);
            }
        }
    }

    /**
     * Sort array using shell sort based on 3x+1 sequence
     *
     * @param list  input array.
     * @param left  left index of array. Included in sort
     * @param right right index of array. NOT INCLUDED in sort
     */
    public static <T extends Comparable<T>> void shellSort(List<T> list, int left, int right) {
        int N = right - left;

        /* generate maximum h value */
        int h = 1;
        while (h < N / 3) h = h * 3 + 1;

        /* start sorting */
        while (h >= 1) {
            for (int i = h; i < N; i += h) {
                for (int j = i; j >= h && less(list.get(j), list.get(j - h)); j -= h)
                    swap(list, j, j - h);
            }
            h = h / 3;
        }
    }

    public static <T extends Comparable<T>> void mergeSort(List<T> list, int left, int right) {

    }


    public static <T extends Comparable<T>> void quickSort(List<T> list, int left, int right) {

    }


    private static <T extends Comparable<T>> void swap(List<T> list, int i, int j) {
        var tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }


    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }
}
