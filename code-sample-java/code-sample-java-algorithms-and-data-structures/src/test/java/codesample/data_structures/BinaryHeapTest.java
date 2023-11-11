package codesample.data_structures;

import codesample.algorithms.Sorts;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    @Test
    void test_can_insert_and_retrieve_in_order() {
        var heap = new BinaryHeap<Integer>();

        for (int i = 0; i < 50; i++) {
            heap.add(i);
        }

        for (int i = 49; i >= 0; i--) {
            assertEquals(i, heap.peek());
            assertEquals(i, heap.poll());

            if (i != 0) {
                assertEquals(i - 1, heap.peek());
            }
        }
    }

    @Test
    void test_can_insert_and_retrieve_out_of_order() {
        var heap = new BinaryHeap<Integer>();

        var rnd = new Random();
        var randomElements = new ArrayList<Integer>();

        for (int i = 0; i < 50; i++) {
            randomElements.add(rnd.nextInt());
        }

        for (var e: randomElements) {
            heap.add(e);
        }

        var sortedWithHeap = new ArrayList<Integer>();
        for (int i = 0; i < 50; i++) {
            sortedWithHeap.add(null);
        }
        for (int i = 49; i >= 0; i--) {
            sortedWithHeap.set(i, heap.poll());
        }

        Sorts.mergeSort(randomElements, 0, randomElements.size());

        assertEquals(sortedWithHeap, randomElements);
    }
}
