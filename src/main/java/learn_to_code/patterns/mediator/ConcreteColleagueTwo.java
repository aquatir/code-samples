package learn_to_code.patterns.mediator;

public class ConcreteColleagueTwo extends Colleague {

    public ConcreteColleagueTwo(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String message) {
        System.out.println("Colleague two send message: " + message);
        super.getMediator().send(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println("Colleague two received message: " + message);
    }
}
