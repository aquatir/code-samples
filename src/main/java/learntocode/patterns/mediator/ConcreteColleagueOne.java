package learntocode.patterns.mediator;

public class ConcreteColleagueOne extends Colleague {

    public ConcreteColleagueOne(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String message) {
        System.out.println("Colleague one send message: " + message);
        super.getMediator().send(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println("Colleague one received message: " + message);
    }
}
