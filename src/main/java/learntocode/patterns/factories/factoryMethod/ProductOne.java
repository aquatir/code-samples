package learntocode.patterns.factories.factoryMethod;

public class ProductOne implements Product {
    public ProductOne (int value) {
        System.out.println("Product 1 created with factory specific value " + value);
    }
}
