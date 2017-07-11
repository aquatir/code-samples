package learn_to_code.patterns.MVC_gui;

public interface ModelInterface {
    int getDisplayedNumber();
    void setDisplayedNumber(int number);
    void registerObserver (Observer obs);
    void removeObserver(Observer obs);
    void notifyObservers();
}
