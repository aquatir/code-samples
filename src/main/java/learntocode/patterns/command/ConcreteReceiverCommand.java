package learntocode.patterns.command;

public class ConcreteReceiverCommand implements Command {

    Receiver receiver;

    public ConcreteReceiverCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.performAction();
    }

    @Override
    public void undo() {
        receiver.performReversedAction();
    }
}
