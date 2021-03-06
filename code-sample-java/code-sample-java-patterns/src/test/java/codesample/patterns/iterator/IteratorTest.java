package codesample.patterns.iterator;

import org.junit.Before;
import org.junit.Test;
import patterns.iterator.Iterator;
import patterns.iterator.IteratorUser;

import java.util.Random;
import static org.junit.Assert.*;

public class IteratorTest {

    private IteratorUser iteratorUser;
    private Iterator iterator;

    private final int size = 1_000_000;
    private final int randomSeed = 0;

    private Random rnd;

    @Before
    public void beforeTest() {
        rnd = new Random(randomSeed);
        iteratorUser = new IteratorUser(generateTestData());
        iterator = iteratorUser.getIterator();
    }

    @Test
    public void iterateOverIntegerArrayByIteratorTest() {
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(iterator.next(), iteratorUser.getById(i));
            i++;
        }
    }

    @Test
    public void iterateOverIntegerArrayByIndexesTest() {
        for (int i = 0; i < iteratorUser.getLength(); ++i) {
            assertEquals(iterator.next(), iteratorUser.getById(i));
        }
    }

    private Integer[] generateTestData() {
        Integer[] array = new Integer[size];
        for (int i = 0; i<size; ++i) {
            array[i] = rnd.nextInt();
        }
        return array.clone();
    }

}
