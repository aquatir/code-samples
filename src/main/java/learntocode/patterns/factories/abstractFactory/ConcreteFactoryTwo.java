package learntocode.patterns.factories.abstractFactory;

/**
 * Concrete factory knows how to implement ALL(!) methods of AbstractFactory
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
