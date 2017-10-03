package patterns.strategy;

public class StrategyBar implements IStrategyBehavior {

    @Override
    public void executeStrategy() {
        System.out.println("executing Bar strategy");
    }
}
