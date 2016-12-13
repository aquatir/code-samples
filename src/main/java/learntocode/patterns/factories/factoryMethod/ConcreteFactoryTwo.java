package learntocode.patterns.factories.factoryMethod;

public class ConcreteFactoryTwo extends SuperFactory {

    /**
     * Overrides superclass factory method to create Product Objects
     * @param value some random value
     * @return concrete Product
     */
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
