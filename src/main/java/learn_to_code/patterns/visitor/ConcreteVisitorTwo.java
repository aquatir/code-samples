package learn_to_code.patterns.visitor;

public class ConcreteVisitorTwo implements Visitor {
    @Override
    public void visit(ConcreteClientOne clientOne) {
        System.out.println("Vastly different from concrete visitor one, this concrete visitor two is visiting clientOne");
    }

    @Override
    public void visit(ConcreteClientTwo clientTwo) {
        System.out.println("Vastly different from concrete visitor one, this concrete visitor two is visiting clientTwo");
    }
}
