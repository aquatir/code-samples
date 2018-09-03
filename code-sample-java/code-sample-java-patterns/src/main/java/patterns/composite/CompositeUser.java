package patterns.composite;

class CompositeUser {
    public static void main(String[] args) {
        Composite SuperComposite = new Composite("SuperComposite");
        Composite SubCompositeOne = new Composite("SubCompositeOne");
        Component SubCompositeTwo = new Composite("SubCompositeTwo");
        Leaf leaf1 = new Leaf("Leaf One");
        Leaf leaf2 = new Leaf("Leaf Two");
        Leaf leaf3 = new Leaf("Leaf Three");
        Leaf leaf4 = new Leaf("Leaf Four");

        SubCompositeOne.addComponent(leaf1);
        SubCompositeOne.addComponent(leaf2);
        SubCompositeTwo.addComponent(leaf3);
        SubCompositeTwo.addComponent(leaf4);

        SuperComposite.addComponent(SubCompositeOne);
        SuperComposite.addComponent(SubCompositeTwo);

        for (Component comp: SuperComposite.getChildren()) {
            comp.operation();
        }

        System.out.println(" \nDeleting sub composite one and calling operation of everything again\n");
        SubCompositeOne.removeSelf();

        for (Component comp: SuperComposite.getChildren()) {
            comp.operation();
        }

        System.out.println(" \nDeleting leaf 4 and calling operation of everything again\n");
        SubCompositeTwo.remove(leaf4);
        for (Component comp: SuperComposite.getChildren()) {
            comp.operation();
        }
    }
}
