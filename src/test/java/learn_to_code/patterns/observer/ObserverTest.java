package learn_to_code.patterns.observer;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ObserverTest {

    private final int randomSeed = 0;
    private Random rnd;
    private ObserverBar obs1;
    private ObserverBar obs2;
    private SubjectImpl subj;

    @Before
    public void init() {
        rnd = new Random(randomSeed);
        subj = new SubjectImpl();
        obs1 = new ObserverBar();
        obs2 = new ObserverBar();
    }

    @Test
    public void removeObserverTest() {
        subj.registerObserver(obs1);
        subj.registerObserver(obs2);

        subj.setSomeValue(rnd.nextInt());
        subj.notifyObservers();
        subj.removeObserver(obs2);

        int randomValue = rnd.nextInt();
        subj.setSomeValue(randomValue);
        subj.notifyObservers();

        assertEquals(randomValue, obs1.getSomeValue());
        assertNotEquals(randomValue, obs2.getSomeValue());
    }

    @Test
    public void notifyObserverTest() {
        subj.registerObserver(obs1);
        subj.registerObserver(obs2);

        int randomValue = rnd.nextInt();
        subj.setSomeValue(randomValue);
        subj.notifyObservers();

        assertEquals(randomValue, obs1.getSomeValue());
        assertEquals(randomValue, obs2.getSomeValue());
    }
}
