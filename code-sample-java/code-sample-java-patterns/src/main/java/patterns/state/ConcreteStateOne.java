package patterns.state;

/**
 * Takes a {@link Context} as argument to constructor. Implements all required methods.
 * Switch state when operation is called.
 */
public class ConcreteStateOne implements State {

    private final Context obj;

    public ConcreteStateOne(Context obj) {
        this.obj = obj;
    }

    @Override
    public void operation() {
        System.out.println("Calling operation on state 1. Switching state to state 2.");
        obj.setState(obj.getConcreteStateTwo());
    }
}
