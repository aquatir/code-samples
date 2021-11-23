package com.codesample.algo;

import static com.codesample.algo.Utils.shuffle;
import static com.codesample.algo.Utils.swap;

public class QuickSort {

    public void sort(int[] array) {

        // a shuffle will fix bad performance on reverse-sorter arrays
        shuffle(array);
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int from, int to) {
        if (to <= from) return;

        int p = partition(array, from, to);
        sort(array, from, p - 1);
        sort(array, p + 1, to);
    }

    private int partition(int[] array, int from, int to) {
        int left = from;
        int right = to + 1;

        // split on from elements
        int p = array[from];

        while (true) {
            while (array[++left] < p) {
                if (left == to)
                    break;
            }

            while (array[--right] > p) {
                if (right == from) {
                    break;
                }
            }

            if (left >= right) break;
            swap(array, left, right);
        }

        swap(array, from, right);
        return right;
    }

    public static void main(String[] args) {
        var q = new QuickSort();

        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        q.sort(arr);

        for (int x : arr) {
            System.out.print(x + ", ");
        }
    }
}