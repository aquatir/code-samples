package patterns.null_object;

/**
 * Null object allow you to think less about NullPointerException. When implementing this pattern
 * you provide static unmodifiable instance, which can be used when creating an object reference for the first time, e.g.
 * {@code Set<..> k = Collections.EMPTY_SET} <br>
 * instead of <br>
 * {@code Set<..> k = null} <br>
 * <br>
 * You can make your NullInstance methods to either do nothing or to throw an exception. With this approach you
 * control the type of exception (While without null object only NullPointerException could be thrown).
 * <br>
 * This implementation throws UnsupportedOperationException() when a call to getValue() or setValue is made()
 */
public abstract class MyBaseClass {

    static final MyBaseClass NULL_BASE_CLASS_INSTANCE = new MyNullClass();


    private int value;

    public abstract void printStuff();
    public abstract void getRandomInteger();

    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public boolean isNullObject() {
        return this == NULL_BASE_CLASS_INSTANCE;
    }

    public MyBaseClass getNullObject() {
        return NULL_BASE_CLASS_INSTANCE;
    }


    private static class MyNullClass extends MyBaseClass {
        @Override
        public void printStuff()  {

        }

        @Override
        public void getRandomInteger() {
        }

        @Override
        public boolean isNullObject() {
            return true;
        }

        @Override
        public int getValue() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setValue(int value) {
            throw new UnsupportedOperationException();
        }
    }
}
