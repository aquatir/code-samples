package learn_to_code.patterns.memento;

/**
 * Memento is a state saving class for {@link Originator}. If originator has many field to save
 * you can use clone() to make a copy of originator and then revert state with this saved copy.
 *
 * In this concrete example only a single String is used to save state.
 *
 * You use {@link Caretaker} to save and retrieve state of {@link Originator}.
 *
 * Note that {@link Memento} and {@link Caretaker} should generally be in the same package
 * with memento's methods being package-private.
 */
public class Memento {
    private final String state;

    Memento(String state) {
        this.state = state;
    }

    String getState() {
        return state;
    }
}
