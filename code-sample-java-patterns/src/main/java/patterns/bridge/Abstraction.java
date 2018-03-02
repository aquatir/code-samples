package patterns.bridge;

/**
 * This class only knows that an {@link Implementation} is needed for it to work.
 * It does not make assumptions about how {@link Implementation} is going to be used.
 *
 * This allows to change {@link Abstraction} and {@link Implementation} classes to not be dependent on each other.
 * See {@link RefinedAbstraction} which is a subclass of this class for some more details and usage comments.
 * See {@link RunBridge} for bridge pattern examples.
 */
public abstract class Abstraction {

    private Implementation implementation;

    public  Abstraction(Implementation impl) {
        implementation = impl;
    }

    public Implementation getImplementation() {
        return implementation;
    }

    public void implementationSpecificMethod() {};
}
