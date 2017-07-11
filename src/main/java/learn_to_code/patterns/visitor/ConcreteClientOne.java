package learn_to_code.patterns.visitor;

public class ConcreteClientOne extends Client {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
