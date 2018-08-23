package patterns.singleton;


/**
 * The simplest way to create singleton is to actually initialize it at the very beginning.
 */
public class SingletonPreInit {

    private static final SingletonPreInit singletonInstance = new SingletonPreInit();

    private SingletonPreInit() {
        /* do something */
    }

    public static SingletonPreInit getInstance() {
        return singletonInstance;
    }
}
