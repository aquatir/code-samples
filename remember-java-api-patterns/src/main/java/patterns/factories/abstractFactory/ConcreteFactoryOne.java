package patterns.factories.abstractFactory;

/**
 * Concrete factory knows how to implement ALL(!) methods of AbstractFactory
 * but do it in it's own fashion
 */
public class ConcreteFactoryOne implements AbstractFactory {

    @Override
    public ProductOne createProductOne() {
        return new ConcreteProductOneFirst();
    }

    @Override
    public ProductTwo createProductTwo() {
        return new ConcreteProductTwoFirst();
    }

}
