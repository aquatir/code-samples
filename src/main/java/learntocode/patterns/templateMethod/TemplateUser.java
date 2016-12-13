package learntocode.patterns.templateMethod;

/**
 * Template User provides implementation for all abstracts in superclass TemplateHolder
 */
public class TemplateUser extends TemplateHolder{
    @Override
    public void someMethodOne() {
        System.out.println("Concrete implementation of someMethodOne()");
    }

    @Override
    public void someMethodTwo() {
        System.out.println("Concrete implementation of someMethodTwo()");
    }
}
