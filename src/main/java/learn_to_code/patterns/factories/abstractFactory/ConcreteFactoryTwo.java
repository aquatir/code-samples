package learn_to_code.patterns.factories.abstractFactory;

/**
 * Concrete factory knows how to implement ALL(!) methods of AbstractFactory
 * but do it in it's own fashion
 */
public class ConcreteFactoryTwo implements AbstractFactory {
    @Override
    public ProductOne createProductOne() {
        return new ConcreteProductOneSecond();
    }

    @Override
    public ProductTwo createProductTwo() {
        return new ConcreteProductTwoSecond();
    }

}
