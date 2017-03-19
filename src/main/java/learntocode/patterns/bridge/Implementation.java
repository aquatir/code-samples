package learntocode.patterns.bridge;

/**
 * This interface defines a way for {@link Abstraction} to perform some task.
 * Note: {@link Abstraction} does not know about methods used in this implementations.
 * This means that Abstraction class and Implementation class/interface can change in different time.
 *
 * You can use subclasses (Implementations) of this interface in {@link RefinedAbstraction} class
 * to perform some specific tasks. See class {@link RunBridge} for usage examples.
 */
public interface Implementation {
    void implementationMethod();
}
