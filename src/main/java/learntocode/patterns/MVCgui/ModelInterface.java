package learntocode.patterns.MVCgui;

public interface ModelInterface {
    int getDisplayedNumber();
    void setDisplayedNumber(int number);
    void registerObserver (Observer obs);
    void removeObserver(Observer obs);
    void notifyObservers();
}
