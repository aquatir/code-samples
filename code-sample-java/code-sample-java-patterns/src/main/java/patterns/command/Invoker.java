package patterns.command;

/**
 * A command invoker. Note Invoker has no idea what type of commands do we have.
 * All it knows is that it could call command.callCommand() method to exetute it.
 */
class Invoker {
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
