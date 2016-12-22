package learntocode.patterns.composite;

public class CompositeUser {
    public static void main(String[] args) {
        Composite composite = new Composite();
        Leaf leaf1 = new Leaf();
        Leaf leaf2 = new Leaf();

        composite.addComponents(leaf1);
        composite.addComponents(leaf2);

        for (Component comp: composite.getAllComponents()) {
            comp.operation();
        }
        composite.operation();
    }
}
