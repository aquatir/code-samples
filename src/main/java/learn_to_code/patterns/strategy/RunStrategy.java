package learn_to_code.patterns.strategy;

public class RunStrategy {
    private IStrategyBehavior strategy;

    public void setStrategy(IStrategyBehavior strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.executeStrategy();
    }

    public static void main (String[] args) {
        RunStrategy strategy = new RunStrategy();
        strategy.setStrategy(new StrategyFoo());
        strategy.executeStrategy();

        strategy.setStrategy(new StrategyBar());
        strategy.executeStrategy();
    }
}