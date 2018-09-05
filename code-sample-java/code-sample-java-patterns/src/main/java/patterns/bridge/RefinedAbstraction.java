package patterns.bridge;

/**
 * As you can see this class defines 2 method. One of them is using some concrete {@link Implementation}
 * to use implementation-specific method. Another is independent of any interfaces.
 *
 * This class and {@link Implementation} interface subclasses can grow independently of each somepackage
 * which is the main purpose of using bridge pattern
 */
public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementation impl) {
        super(impl);
    }

    @Override
    public void implementationSpecificMethod() {
        super.getImplementation().implementationMethod();
    }

    public void nonImplementationSpecificMethod() {
        System.out.println("Calling non-implementation specific method");
    }

}
