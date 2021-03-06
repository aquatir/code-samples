package patterns.flyweight;

/**
 * This are parameters which are used to create an instance of {@link FlyweightObject}.
 * This can be used as keys to get objects from {@link FlyweightFactory}.
 *
 * Needless to say that this class is redundant. It's up to you to determine
 * how you want to find {@link FlyweightObject} in your {@link FlyweightFactory}
 */
class InitParameters {
    private final int initValue;

    public InitParameters(int value) {
        initValue = value;
    }

    private int getInitValue() {
        return initValue;
    }

    /** it is important to override hashcode and equals in InitParameters class in order to make sure
    * that HashMap in {@link FlyweightFactory} works correctly
     * In somepackage words: Respect hashCode/equals contract! */
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
        return other.getInitValue() == this.getInitValue();
    }
}
