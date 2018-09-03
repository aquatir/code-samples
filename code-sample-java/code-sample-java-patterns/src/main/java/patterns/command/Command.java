package patterns.command;

/**
 * An interface used by concrete commands
 */
interface Command {
    void execute();
    void undo();
}
