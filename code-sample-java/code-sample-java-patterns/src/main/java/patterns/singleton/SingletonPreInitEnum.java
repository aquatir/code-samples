package patterns.singleton;


/**
 * Probably the simplest way to implement singleton is by using a single value enum.
 * Take not, that this kind of enum can be serialized automatically.
 */
public enum SingletonPreInitEnum {
    INSTANCE;

    public void someSingletonMethod() {}
}
