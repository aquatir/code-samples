package learntocode.patterns.factories.factoryMethod;

public class ProductThree implements Product {
    public ProductThree(int value) {
        System.out.println("Product 3 created with factory specific value " + value);
    }
}
