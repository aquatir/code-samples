package patterns.observer;

import java.util.ArrayList;

/**
 * Simple concrete subject implementation. Can add/remove/notify observer
 */
public class SubjectImpl implements Subject {

    private final ArrayList<Observer> observers;
    private int someValue;

    public SubjectImpl() {
        observers = new ArrayList<>();
        someValue = 0;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(someValue);
        }
    }

    public void setSomeValue(int someValue) {
        this.someValue = someValue;
    }

    public int getSomeValue() {
        return someValue;
    }

}
