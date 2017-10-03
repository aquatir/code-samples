package patterns.command;

/**
 * An interface used by concrete commands
 */
public interface Command {
    public void execute();
    public void undo();
}
