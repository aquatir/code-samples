package patterns.facade;

/**
 * Facade simply takes all you unbearably complicated object and create simple methods to call those objects' methods
 * in desired order
 */
class Facade {
    private final SuperHardClassOne classOne;
    private final SuperHardClassTwo classTwo;

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
