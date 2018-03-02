package patterns.factories.factoryMethod;

public class ConcreteCreatorOne extends Creator {
    @Override
    public Product factoryMethod() {
        return new ProductOne();
    }
}
