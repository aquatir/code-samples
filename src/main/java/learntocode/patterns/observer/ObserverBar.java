package learntocode.patterns.observer;

public class ObserverBar implements Observer {

    private int someValue;

    @Override
    public void update(int value) {
        someValue = value;
    }

    public int getSomeValue() {
        return someValue;
    }

}
