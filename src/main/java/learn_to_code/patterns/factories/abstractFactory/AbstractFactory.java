package learn_to_code.patterns.factories.abstractFactory;

/**
 * Abstract factory knows about Products which should be created. <br>
 * ALL subclasses should implement all methods
 */
public interface AbstractFactory {
    ProductOne createProductOne();
    ProductTwo createProductTwo();
}
