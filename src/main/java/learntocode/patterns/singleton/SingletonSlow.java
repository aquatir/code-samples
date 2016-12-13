package learntocode.patterns.singleton;

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
