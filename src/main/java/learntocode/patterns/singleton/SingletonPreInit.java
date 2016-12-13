package learntocode.patterns.singleton;

public class SingletonPreInit {

    private static SingletonPreInit singletonInstance = new SingletonPreInit();

    private SingletonPreInit() {
        /* do something */
    }

    public static SingletonPreInit getInstance() {
        return singletonInstance;
    }
}
