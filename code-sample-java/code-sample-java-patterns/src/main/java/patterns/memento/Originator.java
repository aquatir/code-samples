package patterns.memento;

/**
 * This class hold some state and can save it's state with calls to saveState() method which return {@link Memento}
 * object. To store and retrieve this object you should use {@link Caretaker} class. You pass instance of caretakers
 * with saved memento to restoreState(Caretaker crt) to get state of saved memento instance.
 *
 * {@link Caretaker} can not change {@link Memento}'s field neither can {@link Originator}, so by using
 * this mechanism you ensure, that inner state of {@link Memento} would be unchanged
 */
class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveState() {
        return new Memento(state);
    }

    public void restoreState(Caretaker caretaker) {
        this.state = caretaker.getSavedState();
    }
}
