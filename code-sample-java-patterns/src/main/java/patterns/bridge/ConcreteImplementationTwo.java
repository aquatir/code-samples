package patterns.bridge;

/**
 * This is one of many implementations. See {@link Implementation} for more details
 */
public class ConcreteImplementationTwo implements Implementation{
    @Override
    public void implementationMethod() {
        System.out.println("Implementation two called");
    }
}
