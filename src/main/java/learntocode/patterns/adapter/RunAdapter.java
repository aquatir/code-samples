package learntocode.patterns.adapter;

/**
 * Test our implementation
 */
public class RunAdapter {

    public static void main(String[] args) {
        Adapter adapted = new Adapter(new SomeClass());
        adapted.adaptedMethodOne();
        adapted.adaptedMethodTwo();
    }
}
