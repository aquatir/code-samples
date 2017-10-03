package patterns.template_method;

/**
 * Template User provides implementation for all abstracts in superclass TemplateHolder
 */
public class TemplateUser extends TemplateHolder{
    @Override
    void someMethodOne() {
        System.out.println("Concrete implementation of someMethodOne()");
    }

    @Override
    void someMethodTwo() {
        System.out.println("Concrete implementation of someMethodTwo()");
    }

    @Override
    void optionalMethod() {
        System.out.println("Optional method is called");
    }

    @Override
    boolean userDesiresTo() {
        return true;
    }
}
