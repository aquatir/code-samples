package algorithms;

import codesample.algorithms.Sorts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortsTest {

    private final int size = 10;
    private final int randomSeed = 0;
    private Random rnd;

    private List<Integer> arrTest = new ArrayList<>();
    private List<Integer> arrExp = new ArrayList<>();

    @BeforeEach
    public void initiateList() {
        rnd = new Random(randomSeed);
        arrTest = generateRandomIntegerArray(size);
        arrExp = new ArrayList<>();
        arrExp.addAll(arrTest);
    }

    @Test
    public void insertionSortTest() {

        Sorts.insertionSort(arrTest, 0, size);
        arrExp.sort(Integer::compareTo);

        Assertions.assertEquals(arrExp, arrTest);
    }

    @Test
    public void selectionSortTest() {
        int bob = 1;
        Sorts.selectionSort(arrTest, 0, size);
        arrExp.sort(Integer::compareTo);

        Assertions.assertEquals(arrExp, arrTest);
    }

    @Test
    public void shellSortTest() {
        Sorts.shellSort(arrTest, 0, size);
        arrExp.sort(Integer::compareTo);

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
