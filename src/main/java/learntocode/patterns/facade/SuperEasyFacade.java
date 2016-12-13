package learntocode.patterns.facade;

public class SuperEasyFacade {
    SuperHardClassOne classOne;
    SuperHardClassTwo classTwo;

    public SuperEasyFacade(SuperHardClassOne one, SuperHardClassTwo two) {
        this.classOne = one;
        this.classTwo = two;
    }

    public void callEverything() {
        classOne.someMethodOne();
        classTwo.someMethodOne();
        classTwo.someMethodTwo();
        classOne.someMethodTwo();
    }
}
