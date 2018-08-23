package patterns.command;

class Receiver {
    public void performAction() {
        System.out.println("Do something");
    }
    public void performReversedAction() {
        System.out.println("Do something different");
    }
}
