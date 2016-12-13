package learntocode.patterns.factories.abstractFactory;

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
