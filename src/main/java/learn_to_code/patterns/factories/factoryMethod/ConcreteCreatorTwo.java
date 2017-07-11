package learn_to_code.patterns.factories.factoryMethod;

public class ConcreteCreatorTwo extends Creator {
    @Override
    public Product factoryMethod() {
        return new ProductTwo();
    }
}
