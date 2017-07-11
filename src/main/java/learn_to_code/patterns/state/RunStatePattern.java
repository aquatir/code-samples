package learn_to_code.patterns.state;

/**
 * Test class for state pattern
 */
public class RunStatePattern {
    public static void main(String[] args) {
        Context ctx = new Context();
        ctx.callOperation();
        ctx.callOperation();
        ctx.callOperation();
    }
}
