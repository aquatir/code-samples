package learntocode.patterns.adapter;

public class AdaptedClass implements AdaptOverThisInterface {

    private SomeClass clazz;

    public AdaptedClass (SomeClass clazz){
        this.clazz = clazz;
    }

    public void adaptedMethodOne() {
        clazz.methodOne();
    }

    public void adaptedMethodTwo() {
        clazz.methodTwo();
    }
}
