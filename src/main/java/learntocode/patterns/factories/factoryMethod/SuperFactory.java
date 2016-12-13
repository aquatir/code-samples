package learntocode.patterns.factories.factoryMethod;

public abstract class SuperFactory {

    /**
     * This is a factory method. It allows creating Products in subclasses
     * @param value some random value
     * @return Concrete Product
     */
    public abstract Product createProduct(int value);

}
