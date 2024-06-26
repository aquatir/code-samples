package codesample.algorithms;


import codesample.data_structures.BinaryHeap;

import java.util.ArrayList;
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

    // **********
    // MERGE SORT
    // **********

    /**
     * Merge sort for [left; right). Right is usually the size
     */
    public static <T extends Comparable<T>> void mergeSort(List<T> list, int left, int right) {
        var aux = new ArrayList<T>(list.size());
        aux.addAll(list);
        mergeSort(list, aux, left, right - 1);
    }

    // merge sort for [left; right]
    private static <T extends Comparable<T>> void mergeSort(List<T> list, List<T> aux, int left, int right) {
        if (right <= left) {
            return;
        }

        var mid = left + (right - left) / 2;

        mergeSort(list, aux, left, mid);
        mergeSort(list, aux, mid + 1, right);

        merge(list, aux, left, mid, right);
    }

    /**
     * Merge two lists [left; mid] and [mid+1; right]
     */
    private static <T extends Comparable<T>> void merge(List<T> list, List<T> aux, int lo, int mid, int hi) {

        // assert sorted [lo, mid]
        // assert sorted [mid+1; hi]
        for (int i = lo; i <= hi; i++) {
            aux.set(i, list.get(i));
        }

        // i => index of left sorted half
        // j => index of right sorter half
        // k => insert index
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                list.set(k, aux.get(j));
                j++;
            } else if (j > hi) {
                list.set(k, aux.get(i));
                i++;
            } else if (less(aux.get(j), aux.get(i))) {
                list.set(k, aux.get(j));
                j++;
            } else {
                list.set(k, aux.get(i));
                i++;
            }
        }
    }

    // **********
    // QUICK SORT
    // **********

    // Sorts [left; right)
    // Algo:
    // - shuffle to randomize
    // - partition: no larger to left of J, no smaller to right of J
    // - sort each piece
    public static <T extends Comparable<T>> void quickSort(List<T> list, int left, int right) {
        Shuffle.shuffle(list, left, right);
        quickSortInternal(list, left, right - 1);
    }

    // sorts [left; right]
    private static <T extends Comparable<T>> void quickSortInternal(List<T> list, int left, int right) {
        if (left >= right) {
            return;
        }
        var j = partition(list, left, right);
        quickSortInternal(list, left, j - 1);
        quickSortInternal(list, j + 1, right);
    }

    private static <T extends Comparable<T>> int partition(List<T> list, int lo, int hi) {
        int left = lo;          // will increment instantly
        int right = hi + 1;     // will decrement instantly

        // move left pointer to preserve left < lo, then more right to preserve right > lo , then swap
        // at the end swap lo with right pointer, because it now points to correct place
        while (true) {
            while (less(list.get(++left), list.get(lo))) {
                if (left == hi) {
                    break;
                }
            }
            while (less(list.get(lo), list.get(--right))) {
                if (right == lo) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            swap(list, left, right);
        }

        swap(list, lo, right);
        return right;
    }

    /**
     * Sorts at [left, right)
     * This implementation creates a separate heap for sorting.
     * Non-native implementation will re-arrange elements in heap-order first because this won't require extra space
     */

    public static <T extends Comparable<T>> void heapSortNaive(List<T> list, int left, int right) {
        var binaryHeap = new BinaryHeap<T>();

        for (int i = left; i < right; i++) {
            binaryHeap.add(list.get(i));
        }

        for (int i = right - 1; i >= left; i--) {
            list.set(i, binaryHeap.poll());
        }
    }

    /**
     * Sorts at [left, right)
     * This implementation sorts an array in-place without extra memory
     */
    public static <T extends Comparable<T>> void heapSort(List<T> list, int left, int right) {
        // step 1: heapify an array
        // step 2: start moving top elements and swapping similar to `sink` operation in a heap

        // we don't need to heapify the last level of BinaryHeap because last level == heap of 1 == already heapify
        int totalElements = right - left + 1;
        int withLastLevelRemoved = totalElements / 2;

        int maxRightIndex = right - 1;
        for (int i = maxRightIndex - withLastLevelRemoved; i >= left; i--) {
            sink(list, i, maxRightIndex);
        }

        for (int i = maxRightIndex; i > left; i--) {
            swap(list, left, i);
            sink(list, left, i - 1);
        }

    }

    private static <T extends Comparable<T>> void sink(List<T> list, int i, int maxIndex) {
        var leftChild = leftChildIndex(i);
        var rightChild = rightChildIndex(i);

        // if leftChild is more than max index, right child is also more
        // there is nowhere to sink, so we exist now
        if (leftChild > maxIndex) {
            return;
        }

        // start assuming that the left child is larger of two
        // if there is no right child, lake left
        // if left child is smaller, take right
        int largerOfChildren = leftChild;
        if (rightChild > maxIndex) {
            // largerOfChildren = leftChild; // aka do nothing
        } else if (less(list.get(leftChild), list.get(rightChild))) {
            largerOfChildren = rightChild;
        }

        if (!less(list.get(i), list.get(largerOfChildren))) {
            return; // if k (element at the top) is not less than larger children => nothing left to do
        } else {
            // else, swap, continue
            swap(list, i, largerOfChildren);
            sink(list, largerOfChildren, maxIndex);
        }
    }

    private static int leftChildIndex(int k) {
        return ((k + 1) * 2) - 1;
    }

    private static int rightChildIndex(int k) {
        return (k + 1) * 2;
    }

    private static <T extends Comparable<T>> void swap(List<T> list, int i, int j) {
        var tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }


    public static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }
}
