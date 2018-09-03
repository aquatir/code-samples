package patterns.facade;

/**
 * Simple test class for Facade pattern
 */
class RunFacade {
    public static void main(String[] args) {
        Facade facade = new Facade(new SuperHardClassOne(), new SuperHardClassTwo());
        facade.callEverything();
    }
}
