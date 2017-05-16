package learntocode.patterns.composite;

public class CompositeUser {
    public static void main(String[] args) {
        Composite composite = new Composite();
        Leaf leaf1 = new Leaf();
        Leaf leaf2 = new Leaf();

        composite.addComponent(leaf1);
        composite.addComponent(leaf2);

        for (Component comp: composite.getChildren()) {
            comp.operation();
        }

        composite.removeThis();
        System.out.println("kek");
        for (Component comp: composite.getChildren()) {
            comp.operation();
        }
    }
}
