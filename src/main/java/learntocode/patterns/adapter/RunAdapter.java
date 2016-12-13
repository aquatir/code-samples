package learntocode.patterns.adapter;

public class RunAdapter {

    public static void main(String[] args) {
        AdaptedClass adapted = new AdaptedClass(new SomeClass());
        adapted.adaptedMethodOne();
        adapted.adaptedMethodTwo();
    }
}
