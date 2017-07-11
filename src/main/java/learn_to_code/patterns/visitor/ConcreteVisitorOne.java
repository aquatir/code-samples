package learn_to_code.patterns.visitor;

public class ConcreteVisitorOne implements Visitor {
    @Override
    public void visit(ConcreteClientTwo clientTwo) {
        System.out.println("Concrete visitor one is visiting ClientTwo");
    }

    @Override
    public void visit(ConcreteClientOne clientOne) {
        System.out.println("Concrete visitor one is visiting ClientOne");
    }
}
