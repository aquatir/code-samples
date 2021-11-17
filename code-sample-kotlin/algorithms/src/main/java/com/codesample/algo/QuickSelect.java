package com.codesample.algo;

import static com.codesample.algo.Utils.swap;

/**
 * Select Kth largest elements
 */
public class QuickSelect {

    public int select(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;

        while (right > left) {
            int j = partition(array, left, right);

            if (j < k) left = j + 1;
            if (j > k) right = j - 1;
            else return array[k];
        }

        return array[k];
    }

    // same code as in QuickSort
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
        var q = new QuickSelect();
        System.out.println(q.select(new int[]{2, 4, 6, 8, 10, 12}, 0)); // expected == 8 (k is counter from zero, e.g. k = 0 => first smallest elements
    }
}
