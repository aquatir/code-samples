package patterns.mediator;

import java.util.ArrayList;

public class ConcreteMediator implements Mediator {

    private final ArrayList<Colleague> collegues;

    public  ConcreteMediator() {
        collegues = new ArrayList<>();
    }

    @Override
    public void addColleague(Colleague newColleague) {
        collegues.add(newColleague);
    }

    @Override
    public void send(String message, Colleague sender) {
        for (Colleague receiver: collegues) {

            /* do not send messages to self */
            if (receiver != sender)
                receiver.receive(message);
        }
    }
}
