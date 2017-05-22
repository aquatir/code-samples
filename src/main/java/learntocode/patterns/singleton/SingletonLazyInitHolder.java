package learntocode.patterns.singleton;


public class SingletonLazyInitHolder  {
    private static class InstanceHolder {
        public static SingletonLazyInitHolder holder = new SingletonLazyInitHolder();
    }

    public static SingletonLazyInitHolder getInstance() {
        return InstanceHolder.holder;
    }
}
