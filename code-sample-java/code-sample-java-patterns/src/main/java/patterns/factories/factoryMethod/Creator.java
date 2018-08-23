package patterns.factories.factoryMethod;

/**
 * Creator class uses factoryMethod to create an instance of {@link Product}
 * If you want to create specific Products in your classes, you can pass along to those classes different subclasses of Creator
 */
abstract class Creator {
    public abstract Product factoryMethod();
}
