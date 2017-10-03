package learn_to_code.patterns.iterator;

import org.junit.Before;
import org.junit.Test;
import patterns.iterator.Iterator;
import patterns.iterator.IteratorUser;

import java.util.Random;
import static org.junit.Assert.*;

public class IteratorTest {

    IteratorUser iteratorUser;
    Iterator iterator;

    int size = 1_000_000;
    int randomSeed = 0;

    Random rnd;

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

    public Integer[] generateTestData() {
        Integer[] array = new Integer[size];
        for (int i = 0; i<size; ++i) {
            array[i] = rnd.nextInt();
        }
        return array.clone();
    }

}
