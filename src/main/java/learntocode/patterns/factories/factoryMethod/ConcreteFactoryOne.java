package learntocode.patterns.factories.factoryMethod;

/**
 * Overrides superclass factory method to create Product Objects
 */
public class ConcreteFactoryOne extends SuperFactory {

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
