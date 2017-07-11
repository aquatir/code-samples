package learn_to_code.patterns.strategy;

public class StrategyBar implements IStrategyBehavior {

    @Override
    public void executeStrategy() {
        System.out.println("executing Bar strategy");
    }
}
