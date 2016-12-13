package learntocode.patterns.factories.simpleFactory;

public class Factory {

    public Factory() {
        /* some init */
    }

    public Product createProduct(int value) {
        switch (value) {
            case 1:
                return new ProductOne();
            case 2:
                return new ProductTwo();
            default:
                throw new IllegalArgumentException("Wrong product number");
        }

    }
}