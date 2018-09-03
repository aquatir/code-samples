package patterns.flyweight;

/**
 * Flyweight Object class is a class which should have many but limited number of instances
 * Whenever an instance on this class exists there should be no need to create another one
 * as those instances are interchangeable.
 *
 * You can actually get multiple benefits from using Flyweight pattern:
 * 1. If 2 object created with the same input parameters are identical, flyweight allows you to only have one
 * instance of an object
 * 2. If object creating is taking a lot of time AND statement from item 1 is true, you can save some time by
 * giving existing objects to user, instead of creating new ones;
 */
public class FlyweightObject {

    /**
     * Actual object initialize a lot of different parts
     * @param inputParameters
     */
    public FlyweightObject(InitParameters inputParameters) {
        int someIntegerValue = 5;
        String someStringValue = "bob";

        /* some crazy computation may or may not take place here.*/
    }
}
