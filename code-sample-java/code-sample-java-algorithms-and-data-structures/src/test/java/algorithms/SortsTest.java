package algorithms;

import codesample.algorithms.Sorts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SortsTest {

    private final int SIZE = 5_000;
    private Random rnd;

    private List<Integer> arrTest = new ArrayList<>();
    private List<Integer> arrExp = new ArrayList<>();

    @BeforeEach
    public void initiateList() {
        rnd = new Random(0);
        arrTest = generateRandomIntegerArray(SIZE);
        arrExp = new ArrayList<>();
        arrExp.addAll(arrTest);
        arrExp.sort(Integer::compareTo);
    }

    @Test
    void bubbleSortTest() {

        Sorts.bubbleSort(arrTest, 0, SIZE);
        Assertions.assertEquals(arrExp, arrTest);
    }

    @Test
    void insertionSortTest() {
        Sorts.insertionSort(arrTest, 0, SIZE);
        Assertions.assertEquals(arrExp, arrTest);
    }

    @Test
    void selectionSortTest() {
        Sorts.selectionSort(arrTest, 0, SIZE);
        Assertions.assertEquals(arrExp, arrTest);
    }

    @Test
    void shellSortTest() {
        Sorts.shellSort(arrTest, 0, SIZE);
        Assertions.assertEquals(arrExp, arrTest);
    }

    @Test
    void mergeSortTest() {
        Sorts.mergeSort(arrTest, 0, SIZE);
        Assertions.assertEquals(arrExp, arrTest);
    }

//    @Test
//    void testMerge() {
//        var arr = new ArrayList<Integer>(List.of(1, 4, 5, 7, 2, 3, 6, 8));
//        var aux = new ArrayList<Integer>(arr.size());
//        for (var e: arr) {
//            aux.add(e);
//        }
//        Sorts.merge(arr, aux, 0, 3, 7);
//
//        System.out.println(arr);
//    }

    @Test
    void quickSortTest() {
        Sorts.quickSort(arrTest, 0, SIZE);
        Assertions.assertEquals(arrExp, arrTest);
    }

    private List<Integer> generateRandomIntegerArray(int size) {
        var array = new ArrayList<Integer>(size);
        for (int i = 0; i < size; ++i) {
            array.add(rnd.nextInt());
        }
        return array;
    }
}
