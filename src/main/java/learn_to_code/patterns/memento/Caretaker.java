package learn_to_code.patterns.memento;

/**
 * Caretaker class can save {@link Memento} objects, but can not create them.
 * Only {@link Originator} can create memento object and only {@link Caretaker} can retrieve them.
 */
public class Caretaker {
    private Memento memento;

    public String getSavedState() {
        return memento.getState();
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
