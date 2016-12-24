package learntocode.patterns.singleton;

/**
 * The slowest and most clear singleton implementation. Problem here is that whenever you try to
 * retrieve singleton, you get a synchronization block.
 */
public class SingletonSlow {

    private static SingletonSlow singletonInstance;

    private SingletonSlow() {
        /* do something */
    }

    public static synchronized SingletonSlow getInstance() {
        if (singletonInstance == null)
            singletonInstance = new SingletonSlow();
        return singletonInstance;
    }
}
