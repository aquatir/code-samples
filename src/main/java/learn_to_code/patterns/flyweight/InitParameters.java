package learn_to_code.patterns.flyweight;

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

    public int getInitValue() {
        return initValue;
    }

    /** it is important to override hashcode and equals in InitParameters class in order to make sure
    * that HashMap in {@link FlyweightFactory} works correctly
     * In other words: Respect hashCode/equals contract! */
    @Override
    public int hashCode() {

        System.out.println("Counting hashcode");
        return initValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InitParameters))
            return false;

        InitParameters other = (InitParameters) obj;
        if (other.getInitValue() != this.getInitValue())
            return false;

        return true;
    }
}
