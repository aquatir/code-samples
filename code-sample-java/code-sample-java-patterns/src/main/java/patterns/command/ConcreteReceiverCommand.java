package patterns.command;

/**
 * Concrete command used to make calls to {@link Receiver}.
 * Take you can actually create a composite of commands stacking one into the other and calling their execute() in whichever order you with
 */
public class ConcreteReceiverCommand implements Command {

    private final Receiver receiver;

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
