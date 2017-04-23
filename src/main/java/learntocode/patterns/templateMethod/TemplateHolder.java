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
        System.out.println("User desired to always do this step in the same way");
    }


    /** Note: next 4 methods are package-private. Usually you would want to put TemplateHolder and
    * it's subclasses into a separate package. This way, when you instantiate TemplateUser in some
     * other package, you could only make a call to executionPath() */

    /* this too methods should always be overriden */
    abstract void someMethodOne();
    abstract void someMethodTwo();

    /* If overriden, this method would be called somewhere in executionPath(), but default behavior is: do nothing*/
    void optionalMethod() {};

    /* If this method is overriden to return true, a call to concreteMethodTwo() would accure as it's mentioned
    * int executionPath() */
    boolean userDesiresTo() {
        return false;
    }

}
