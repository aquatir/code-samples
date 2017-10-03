package patterns.factories.factoryMethod;

/**
 * Creator class uses factoryMethod to create an instance of {@link learn_to_code.patterns.factories.factoryMethod.Product}
 * If you want to create specific Products in your classes, you can pass along to those classes different subclasses of Creator
 */
public abstract class Creator {
    public abstract Product factoryMethod();
}
