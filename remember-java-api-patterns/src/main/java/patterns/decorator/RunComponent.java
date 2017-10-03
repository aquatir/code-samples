package patterns.decorator;

/**
 * Some test class. Launch and see how components are getting executed from bottom to top;
 */
public class RunComponent {

    public static void main(String[] args) {
        Component dec = new Decorator();
        dec = new ConcreteComponent1(dec);
        dec = new ConcreteComponent3(dec);
        dec = new ConcreteComponent2(dec);
        dec = new ConcreteComponent1(dec);
        dec = new ConcreteComponent3(dec);
        dec = new ConcreteComponent2(dec);

        dec.componentFunction();
    }
}
