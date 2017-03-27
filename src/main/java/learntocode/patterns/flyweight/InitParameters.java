package learntocode.patterns.flyweight;

/**
 * This are parameters which are used to create an instance of {@link FlyweightObject}.
 * This can be used as keys to get objects from {@link FlyweightFactory}.
 *
 * Needless to say that this class is redundant. It's up to you to determine
 * how you want to find {@link FlyweightObject} in your {@link FlyweightFactory}
 */
public class InitParameters {
    private int initValue;

    public InitParameters(int value) {
        initValue = value;
    }

    /** it is important to override hashcode in InitParameters class in order to make sure
    * that HashMap in {@link FlyweightFactory} works correctly*/
    @Override
    public int hashCode() {
        return initValue;
    }
}
