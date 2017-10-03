package patterns.null_object;

public class RunNullObject {
    public static void main(String[] args) {
        /* this will print nothing and always get the same result */
        MyBaseClass cl = MyBaseClass.NULL_BASE_CLASS_INSTANCE;
        cl.printStuff();
        cl.getRandomInteger();
    }
}
