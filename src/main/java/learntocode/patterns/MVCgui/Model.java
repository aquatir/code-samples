package learntocode.patterns.MVCgui;

import java.util.ArrayList;

/**
 * Model represent the inner state of view.
 * You can get current state from model but should not modify it directly.
 * {@link Controller} is used to modify state of model.
 */
public class Model implements ModelInterface {

    private ArrayList<Observer> observedViews;
    private int displayedNumber;

    public Model() {
        observedViews = new ArrayList<Observer>();
        displayedNumber = 0;
    }

    @Override
    public void registerObserver(Observer obs) {
        observedViews.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observedViews.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observedViews.size(); ++i) {
            observedViews.get(i).update();
        }
    }

    @Override
    public void setDisplayedNumber(int number) {
        displayedNumber = number;
        notifyObservers();
    }


    @Override
    public int getDisplayedNumber() {
        return displayedNumber;
    }
}
