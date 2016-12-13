package learntocode.patterns.factories.factoryMethod;

public class ConcreteFactoryOne extends SuperFactory {

    public ConcreteFactoryOne(int value) {
        setConcreteFactorySpecificValue(value);
    }

    public Product createProduct(int value) {
        switch (value) {
            case 2:
                return new ProductTwo(getConcreteFactorySpecificValue());
            case 1:
                return new ProductOne(getConcreteFactorySpecificValue());
            default:
                throw new IllegalArgumentException("Wrong product number");
        }
    }

    @Override
    public void concreteFactorySpecificMethod() {
        System.out.println("This method is specific for concrete factory one");
    }
}
