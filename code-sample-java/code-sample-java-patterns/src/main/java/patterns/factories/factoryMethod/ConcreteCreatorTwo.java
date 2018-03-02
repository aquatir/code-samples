package patterns.factories.factoryMethod;

public class ConcreteCreatorTwo extends Creator {
    @Override
    public Product factoryMethod() {
        return new ProductTwo();
    }
}
