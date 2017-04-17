package learntocode.patterns.visitor;

public class ConcreteClientOne extends Client {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
