package codesample.data_structures;

import java.util.ArrayList;

import static codesample.algorithms.Sorts.less;

/**
 * Binary Heap == complete binary tree. It's perfectly balanced expect for the last level
 * <p>
 * Parent key is not smaller than children's key.
 * <p>
 * Will start the array with a[1]. Parent of node is at index k/2.
 * Children of node at k are at 2k and 2k+1
 */
public class BinaryHeap<T extends Comparable<T>> {

    ArrayList<T> arr;

    public BinaryHeap() {
        this.arr = new ArrayList<>();
    }

    public T peek() {
        return arr.get(0);
    }

    public T poll() {
        var returned = arr.get(0);

        // swap current max with the lowest elements, them remove "lowest element" with is now max
        this.swap(0, size() - 1);
        arr.remove(size() - 1);

        // now the first element may be out of place, we must sink it
        this.sink(0);

        return returned;
    }

    public void add(T newValue) {
        var indexOfInsert = size();

        // add elements at the very end, then swim it to the top if possible
        arr.add(newValue);
        swim(indexOfInsert);
    }

    /**
     * Takes the elements in lower parts of the tree and try to swim it higher
     */
    private void swim(int k) {

        // if k already at the top, nowhere else to swim
        while (k != 0) {

            // compare with parent
            var parentIndex = parentIndex(k);

            // if parent is smaller, swap and keep comparing
            // or break out instantly
            if (less(arr.get(parentIndex), arr.get(k))) {
                swap(parentIndex, k);
                k = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * Takes the elements from the top of the tree and sink it lower
     */
    private void sink(int k) {
        var leftChild = leftChildIndex(k);
        var rightChild = rightChildIndex(k);

        // if leftChild is more than max index, right child is also more
        // there is nowhere to sink, so we exist now
        if (leftChild > maxIndex()) {
            return;
        }

        // start assuming that the left child is larger of two
        // if there is no right child, lake left
        // if left child is smaller, take right
        int largerOfChildren = leftChild;
        if (rightChild > maxIndex()) {
            // largerOfChildren = leftChild; // aka do nothing
        } else if (less(arr.get(leftChild), arr.get(rightChild))) {
            largerOfChildren = rightChild;
        }

        if (!less(arr.get(k), arr.get(largerOfChildren))) {
            return; // if k (element at the top) is not less than larger children => nothing left to do
        } else {
            // else, swap, continue
            swap(k, largerOfChildren);
            sink(largerOfChildren);
        }

    }

    private int size() {
        return this.arr.size();
    }

    private int maxIndex() {
        return this.arr.size() - 1;
    }

    private int leftChildIndex(int k) {
        return ((k + 1) * 2) - 1;
    }

    private int rightChildIndex(int k) {
        return (k + 1) * 2;
    }

    private int parentIndex(int k) {
        return (k - 1) / 2;
    }

    private void swap(int i, int k) {
        var tmp = arr.get(i);
        arr.set(i, arr.get(k));
        arr.set(k, tmp);
    }
}
