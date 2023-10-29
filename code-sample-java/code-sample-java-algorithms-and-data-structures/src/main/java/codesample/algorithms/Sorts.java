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
     * Sort array using bubble sort. In bubble the larges elements it "bubbled up" to the top
     * Terminates early if previous iteration had no swaps
     *
     * @param array input array.
     * @param left  left index of array. Included in sort
     * @param right right index of array. NOT INCLUDED in sort
     */
    public static <T extends Comparable<T>> void bubbleSort(List<T> array, int left, int right) {
        for (int i = left; i < right; ++i) {
            var swap = false;
            for (int j = left + 1; j < right - i; ++j) {
                if (less(array.get(j), array.get(j - 1))) {
                    swap(array, j - 1, j);
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    /**
     * Sort array using insertion sort. In insertion sort after ith iteration, the subarray [left,i] is sorted
     *
     * @param array input array.
     * @param left  left index of array. Included in sort
     * @param right right index of array. NOT INCLUDED in sort
     */
    public static <T extends Comparable<T>> void insertionSort(List<T> array, int left, int right) {
        for (int i = left; i < right; i++) {
            for (int j = i; j >= left + 1; j--) {
                if (less(array.get(j), array.get(j - 1))) {
                    swap(array, j, j - 1);
                } else {
                    break;
                }
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
        int n = right - left;

        /* generate maximum h value */
        int h = 1;
        while (h < n / 3) {
            h = h * 3 + 1;
        }

        /* start sorting */
        while (h >= 1) {
            for (int i = h; i < n; i += h) {
                for (int j = i; j >= h && less(list.get(j), list.get(j - h)); j -= h) {
                    swap(list, j, j - h);
                }
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
