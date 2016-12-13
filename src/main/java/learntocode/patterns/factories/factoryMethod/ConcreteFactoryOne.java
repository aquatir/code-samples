package learntocode.patterns.factories.factoryMethod;

public class ConcreteFactoryOne extends SuperFactory {

    /**
     * Overrides superclass factory method to create Product Objects
     * @param value some random value
     * @return concrete Product
     */
    @Override
    public Product createProduct(int value) {
        switch (value) {
            case 2:
                return new ProductTwo(value);
            case 1:
                return new ProductOne(value);
            default:
                throw new IllegalArgumentException("Wrong product number");
        }
    }
}
