package patterns.visitor;

/**
 * Client class can accept {@link Visitor}. See visitor class for more info
 */
abstract class Client {
    public abstract void accept(Visitor v);
}
