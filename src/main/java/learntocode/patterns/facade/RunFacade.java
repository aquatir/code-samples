package learntocode.patterns.facade;


public class RunFacade {
    public static void main(String[] args) {
        SuperEasyFacade facade = new SuperEasyFacade(new SuperHardClassOne(), new SuperHardClassTwo());
        facade.callEverything();
    }
}
