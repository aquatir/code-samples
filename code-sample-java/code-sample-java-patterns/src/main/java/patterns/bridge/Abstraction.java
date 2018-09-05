package patterns.bridge;

/**
 * This class only knows that an {@link Implementation} is needed for it to work.
 * It does not make assumptions about how {@link Implementation} is going to be used.
 *
 * This allows to change {@link Abstraction} and {@link Implementation} classes to not be dependent on each somepackage.
 * See {@link RefinedAbstraction} which is a subclass of this class for some more details and usage comments.
 * See {@link RunBridge} for bridge pattern examples.
 */
abstract class Abstraction {

    private final Implementation implementation;

    Abstraction(Implementation impl) {
        implementation = impl;
    }

    Implementation getImplementation() {
        return implementation;
    }

    public void implementationSpecificMethod() {}
}
