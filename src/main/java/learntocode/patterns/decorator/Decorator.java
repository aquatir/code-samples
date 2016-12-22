package learntocode.patterns.decorator;

/**
 * A decorator is Component which should begin the line of execution
 * (e.g. you first create a decorator and then create components taking this decorator as argument)
 */
public class Decorator implements Component{

    @Override
    public void componentFunction() {
        System.out.println("Decorator");
    }
}
