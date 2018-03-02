package patterns.MVC_gui;

import java.util.LinkedList;

/**
 * Model represent the inner state of view.
 * You can get current state from model but should not modify it directly.
 * {@link Controller} is used to modify state of model.
 */
public class Model implements ModelInterface {

    private LinkedList<Observer> observedViews;
    private int displayedNumber;

    /**
     * Create LinkedList of view observers when created
     */
    public Model() {
        observedViews = new LinkedList<Observer>();
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

    /**
     * Method should only be called from controller. The controller is specified when creating {@link View}
     * Controller does not trigger redraw. It should be done in {@link View}
     * @param number number to be displayed.
     */
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
