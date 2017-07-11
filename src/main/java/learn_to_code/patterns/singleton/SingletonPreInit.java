package learn_to_code.patterns.singleton;


/**
 * The simplest way to create singleton is to actually initialize it at the very beginning.
 */
public class SingletonPreInit {

    private static SingletonPreInit singletonInstance = new SingletonPreInit();

    private SingletonPreInit() {
        /* do something */
    }

    public static SingletonPreInit getInstance() {
        return singletonInstance;
    }
}
