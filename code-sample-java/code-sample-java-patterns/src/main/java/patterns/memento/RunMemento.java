package patterns.memento;

class RunMemento {

    public static void main (String[] args) {
        Originator orig = new Originator();
        orig.setState("State one");

        Caretaker savedState = new Caretaker();
        savedState.setMemento(orig.saveState());

        /* now 'State one' is saved in caretaker. Let's change state and then restore origin state */

        orig.setState("State two");
        System.out.println("Current state: " + orig.getState());

        orig.restoreState(savedState);
        System.out.print("Current state: " + orig.getState());
    }
}
