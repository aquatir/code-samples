package learntocode.patterns.singleton;

/**
 * The JVM defers creation of holder until it's actually needed, so this approach works as a charm.
 */
public class SingletonLazyInitHolder  {
    private static class InstanceHolder {
        public static SingletonLazyInitHolder holder = new SingletonLazyInitHolder();
    }

    public static SingletonLazyInitHolder getInstance() {
        return InstanceHolder.holder;
    }
}
