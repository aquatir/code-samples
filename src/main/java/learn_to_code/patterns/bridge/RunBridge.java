package learn_to_code.patterns.bridge;

public class RunBridge {
    public static void main(String[] args) {

        System.out.println("Creating 2 abstractions with different implementations-specific methods");
        RefinedAbstraction refinedAbstractionOne = new RefinedAbstraction(new ConcreteImplementationOne());
        RefinedAbstraction refinedAbstractionTwo = new RefinedAbstraction(new ConcreteImplementationTwo());

        System.out.println("\n" +
                "Calling implementations specific method on each of implementations.\n" +
                "They would produce different results:");
        refinedAbstractionOne.implementationSpecificMethod();
        refinedAbstractionTwo.implementationSpecificMethod();

        System.out.println("\n" +
                "Now calling non-implementations specific methods.\n" +
                "They should produce the same result:");
        refinedAbstractionOne.nonImplementationSpecificMethod();
        refinedAbstractionTwo.nonImplementationSpecificMethod();
    }
}
