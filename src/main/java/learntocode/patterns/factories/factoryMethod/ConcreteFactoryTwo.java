package learntocode.patterns.factories.factoryMethod;

/**
 * Overrides superclass factory method to create Product Objects
 */
public class ConcreteFactoryTwo extends SuperFactory {

    @Override
    public Product createProduct(int value) {
        switch (value) {
            case 3:
                return new ProductThree(value);
            case 4:
                return new ProductFour(value);
            default:
                throw new IllegalArgumentException("Wrong product number");
        }
    }

}
