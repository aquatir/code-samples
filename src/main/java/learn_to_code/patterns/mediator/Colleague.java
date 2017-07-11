package learn_to_code.patterns.mediator;


/**
 * Colleagues use {@link Mediator} to send their messages with send(String message) method.
 * Mediator determines who should receive the message. In most basic implementation all Colleagues except the one
 * who send the message would do so.
 *
 * Call to receive method should only be done by mediator.
 */
public abstract class Colleague {
    private Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public Mediator getMediator() {
        return  mediator;
    }

    public abstract void receive(String message);
}
