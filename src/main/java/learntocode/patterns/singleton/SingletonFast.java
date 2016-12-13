package learntocode.patterns.singleton;

public class SingletonFast {

    private static SingletonFast singletonInstance;

    private SingletonFast() {
        /* do something */
    }

    public static SingletonFast getInstance() {
        if (singletonInstance == null)
            synchronized (SingletonFast.class) {
                if (singletonInstance == null) {
                    singletonInstance = new SingletonFast();
                }
            }

        return singletonInstance;
    }
}
