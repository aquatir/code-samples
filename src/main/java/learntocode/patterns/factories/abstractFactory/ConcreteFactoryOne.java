package learntocode.patterns.factories.abstractFactory;

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
