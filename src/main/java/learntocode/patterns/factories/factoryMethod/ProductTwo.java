package learntocode.patterns.factories.factoryMethod;

public class ProductTwo implements Product {
    public ProductTwo (int value) {
        System.out.println("Product 2 created with factory specific value " + value);
    }
}
