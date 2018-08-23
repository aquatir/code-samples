package patterns.observer;

/**
 * Subject is an interface which work with observers and tell them that
 * something has happened and observers should react to it.
 */
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();

}
