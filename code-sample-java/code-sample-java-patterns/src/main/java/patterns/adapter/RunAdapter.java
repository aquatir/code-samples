package patterns.adapter;

/**
 * Test our implementation
 */
class RunAdapter {

    public static void main(String[] args) {
        Adapter adapted = new Adapter(new SomeClass());
        adapted.adaptedMethodOne();
        adapted.adaptedMethodTwo();
    }
}
