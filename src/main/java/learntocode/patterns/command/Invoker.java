package learntocode.patterns.command;

public class Invoker {
    private Command command;

    public void registerCommand(Command command) {
        this.command = command;
    }

    public void callCommand() {
        command.execute();
    }

    public void callReversedCommand() {
        command.undo();
    }
}
