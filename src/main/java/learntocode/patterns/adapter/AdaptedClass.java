package learntocode.patterns.adapter;

public class AdaptedClass implements AdaptOverThisInterface {

    private SomeClass clazz;

    public AdaptedClass (SomeClass clazz){
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
