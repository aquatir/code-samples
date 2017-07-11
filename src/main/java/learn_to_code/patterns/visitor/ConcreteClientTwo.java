package learn_to_code.patterns.visitor;

public class ConcreteClientTwo extends Client {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
