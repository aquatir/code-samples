package learn_to_code.patterns.singleton;

/**
 * The slowest and most clear singleton implementation. Problem here is that whenever you try to
 * retrieve singleton, you get a synchronization block.
 *
 * BUT ACTUALLY it's not that much of a performance issue.
 */
public class SingletonLazySynchronizationBlock {

    private static SingletonLazySynchronizationBlock singletonInstance;

    private SingletonLazySynchronizationBlock() {
        /* do something */
    }

    public static synchronized SingletonLazySynchronizationBlock getInstance() {
        if (singletonInstance == null)
            singletonInstance = new SingletonLazySynchronizationBlock();
        return singletonInstance;
    }
}
