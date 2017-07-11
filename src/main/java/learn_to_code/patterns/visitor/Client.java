package learn_to_code.patterns.visitor;

/**
 * Client class can accept {@link Visitor}. See visitor class for more info
 */
public abstract class Client {
    public abstract void accept(Visitor v);
}
