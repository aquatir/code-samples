package patterns.state;

/**
 * Test class for state pattern
 *
 * Notice that we call the same {@link Context#callOperation()} while different code is being executed.
 * All state switching happens in concrete {@link State} implementations.
 */
public class RunStatePattern {
    public static void main(String[] args) {
        Context ctx = new Context();
        ctx.callOperation();
        ctx.callOperation();
        ctx.callOperation();
    }
}
