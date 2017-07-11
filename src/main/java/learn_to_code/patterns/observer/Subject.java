package learn_to_code.patterns.observer;

/**
 * Subject is an interface which work with observers and tell them that
 * something has happened and observers should react to it.
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();

}
