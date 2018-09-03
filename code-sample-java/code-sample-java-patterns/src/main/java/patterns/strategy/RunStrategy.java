package patterns.strategy;

class RunStrategy {
    private IStrategyBehavior strategy;

    private void setStrategy(IStrategyBehavior strategy) {
        this.strategy = strategy;
    }

    private void executeStrategy() {
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