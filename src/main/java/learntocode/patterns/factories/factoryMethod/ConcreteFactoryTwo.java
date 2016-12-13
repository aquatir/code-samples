package learntocode.patterns.factories.factoryMethod;

public class ConcreteFactoryTwo extends SuperFactory {

    public ConcreteFactoryTwo(int value) {
        setConcreteFactorySpecificValue(value);
    }

    public Product createProduct(int value) {
        switch (value) {
            case 3:
                return new ProductThree(getConcreteFactorySpecificValue());
            case 4:
                return new ProductFour(getConcreteFactorySpecificValue());
            default:
                throw new IllegalArgumentException("Wrong product number");
        }
    }

    @Override
    public void concreteFactorySpecificMethod() {
        System.out.println("This method is specific for concrete factory two");
    }
}
