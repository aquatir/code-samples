package learntocode.patterns.factories.factoryMethod;

public class ProductFour implements Product {
    public ProductFour(int value) {
        System.out.println("Product 4 created with factory specific value " + value);
    }
}
