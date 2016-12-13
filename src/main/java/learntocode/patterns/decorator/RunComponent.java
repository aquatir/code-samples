package learntocode.patterns.decorator;

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
