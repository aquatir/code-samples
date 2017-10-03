package patterns.state;

/**
 * Takes a {@link Context} as argument to constructor. Implements all required methods.
 * Switch state when operation is called.
 */
public class ConcreteStateTwo implements State {

    Context obj;

    public ConcreteStateTwo(Context obj) {
        this.obj = obj;
    }

    @Override
    public void operation() {
        System.out.println("Calling operation on state 2. Switching state to state 1.");
        obj.setState(obj.getConcreteStateOne());
    }
}
