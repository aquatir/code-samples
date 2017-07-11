package learn_to_code.patterns.mediator;

/**
 * The purpose of Mediator pattern is to allow many object to communicate between each other.
 * Mediator provides an interface which limit all communications that could happen between objects.
 *
 * Mediator subclasses ({@link ConcreteMediator}) should know about all possible subclasses of {@link Colleague}
 * or have a way to register. One way to register them would be observer pattern
 * (This approach is used in JMS for example)
 */
public interface Mediator {
    void send(String message, Colleague sender);
    void addColleague(Colleague colleague);
}
