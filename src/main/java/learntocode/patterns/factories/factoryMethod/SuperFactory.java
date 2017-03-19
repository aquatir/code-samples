package learntocode.patterns.factories.factoryMethod;

/**
 * This is a factory method. It allows creating Products in subclasses by a call the specific creating method.
 * Can actually be an interface if needed.
 */
public abstract class SuperFactory {
    abstract Product createProduct(int value);
}
