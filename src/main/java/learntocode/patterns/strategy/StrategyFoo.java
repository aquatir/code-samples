package learntocode.patterns.strategy;

public class StrategyFoo implements IStrategyBehavior {

    @Override
    public void executeStrategy() {
        System.out.println("executing Foo strategy");
    }

}
