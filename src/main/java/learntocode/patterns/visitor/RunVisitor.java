package learntocode.patterns.visitor;

/**
 * As you can see at this example, both {@link ConcreteClientOne} and {@link ConcreteClientTwo} accept
 * both visitors, but those visitors actions are vastly different and they can make use of
 * clients' data field and method
 */
public class RunVisitor {

    public static void main(String[] args) {
        Client cl1 = new ConcreteClientOne();
        Client cl2 = new ConcreteClientTwo();

        Visitor visitor1 = new ConcreteVisitorOne();
        Visitor visitor2 = new ConcreteVisitorTwo();

        cl1.accept(visitor1);
        cl2.accept(visitor1);

        cl1.accept(visitor2);
        cl2.accept(visitor2);
    }
}
