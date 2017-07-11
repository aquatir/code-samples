package learn_to_code.patterns.facade;

/**
 * Facade simply takes all you unbearably complicated object and create simple methods to call those objects' methods
 * in desired order
 */
public class Facade {
    SuperHardClassOne classOne;
    SuperHardClassTwo classTwo;

    public Facade(SuperHardClassOne one, SuperHardClassTwo two) {
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
