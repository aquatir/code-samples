package patterns.bridge;

/**
 * This is one of many implementations. See {@link Implementation} for more details
 */
public class ConcreteImplementationOne implements Implementation{
    @Override
    public void implementationMethod() {
        System.out.println("Implementation one called");
    }
}
