package learntocode.patterns.observer;

import java.util.ArrayList;

public class SubjectImpl implements Subject {

    ArrayList<Observer> observers;
    int someValue;

    public SubjectImpl() {
        observers = new ArrayList<Observer>();
        someValue = 0;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        observers.remove(index);
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
