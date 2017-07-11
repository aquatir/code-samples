package learn_to_code.patterns.factories.simpleFactory;

/**
 * Simple factory will straightforwardly give you a desired object based on some given value.
 * This value can actually be anything you wish.
 */
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