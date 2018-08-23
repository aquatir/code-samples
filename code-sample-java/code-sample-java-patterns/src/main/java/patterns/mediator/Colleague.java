package patterns.mediator;


/**
 * Colleagues use {@link Mediator} to send their messages with send(String message) method.
 * Mediator determines who should receive the message. In most basic implementation all Colleagues except the one
 * who send the message would do so.
 *
 * Call to receive method should only be done by mediator.
 */
abstract class Colleague {
    private final Mediator mediator;

    Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    Mediator getMediator() {
        return  mediator;
    }

    public abstract void receive(String message);
}
