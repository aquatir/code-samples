package patterns.factories.abstractFactory;

/**
 * Abstract factory knows about Products which should be created. <br>
 * ALL subclasses should implement all methods
 */
interface AbstractFactory {
    ProductOne createProductOne();
    ProductTwo createProductTwo();
}
