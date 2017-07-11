package learn_to_code.patterns.factories.factoryMethod;

public class ConcreteCreatorOne extends Creator {
    @Override
    public Product factoryMethod() {
        return new ProductOne();
    }
}
