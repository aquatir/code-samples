package algorithms;

import codesample.algorithms.Sorts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortsTest {

    private final int size = 10;
    private final int randomSeed = 0;
    private Random rnd;

    private List<Integer> arrTest = new ArrayList<>();
    private List<Integer> arrExp = new ArrayList<>();

    @Before
    public void setRandomSeed() {
        rnd = new Random(randomSeed);
    }

    @Before
    public void initiateList() {
        arrTest = generateRandomIntegerArray(size);
        arrExp = new ArrayList<>();
        arrExp.addAll(arrTest);
    }

    @Test
    public void insertionSortTest() {

        Sorts.insertionSort(arrTest, 0, size);
        arrExp.sort(Integer::compareTo);

        Assert.assertEquals(arrExp, arrTest);
    }

    @Test
    public void selectionSortTest() {
        int bob = 1;
        Sorts.selectionSort(arrTest, 0, size);
        arrExp.sort(Integer::compareTo);

        Assert.assertEquals(arrExp, arrTest);
    }

    @Test
    public void shellSortTest() {
        Sorts.shellSort(arrTest, 0, size);
        arrExp.sort(Integer::compareTo);

        Assert.assertEquals(arrExp, arrTest);
    }

    private List<Integer> generateRandomIntegerArray(int size) {
        var array = new ArrayList<Integer>(size);
        for (int i = 0; i<size; ++i) {
            array.add(rnd.nextInt());
        }
        return array;
    }
}
