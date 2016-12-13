package learntocode.patterns.templateMethod;

/**
 * Template Method states a final path of execution which should not be overridden
 * but allows subclasses to change some methods such as someMethodOne and someMethodTwo in this example. <br><br>
 *
 * Note1: Some methods in template might be optional (like optionalMethod() in this example).
 * Subclass may or may not override them. At the same time SuperClass(TemplateHolder) may or may not provide
 * default implementation for them. <br>
 *
 * Note2: Other method might be hard-coded (like concreteMethodTwo in this implementation) in template path,
 * but subclass can decide whenever or not it wants to use this specific method. <br>
 *
 * Note3: Actually even this hard-coded method can be declared as abstract and implemented in subclass.
 *
 */
public abstract class TemplateHolder {
    public final void executionPath() {
        someMethodOne();
        concreteMethodOne();
        someMethodTwo();
        optionalMethod();
        if (userDesiresTo()) {
            concreteMethodTwo();
        }
    }

    private final void concreteMethodOne() {
        System.out.println("Always do this step in the same way");
    }

    private final void concreteMethodTwo() {
        System.out.println("Always do this step in the same way");
    }

    public abstract void someMethodOne();
    public abstract void someMethodTwo();


    public void optionalMethod() {};
    public boolean userDesiresTo() {
        return false;
    }

}
