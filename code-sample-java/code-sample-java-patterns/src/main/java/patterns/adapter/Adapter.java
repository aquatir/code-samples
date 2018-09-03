package patterns.adapter;

/**
 * This is a class used as adapter for another class using desired interface. See adapted class {@link SomeClass}
 */
public class Adapter implements AdapterInterface {

    private final SomeClass clazz;

    public Adapter(SomeClass clazz){
        this.clazz = clazz;
    }

    @Override
    public void adaptedMethodOne() {
        clazz.methodOne();
    }

    @Override
    public void adaptedMethodTwo() {
        clazz.methodTwo();
    }
}
