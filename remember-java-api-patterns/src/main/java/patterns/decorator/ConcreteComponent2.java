package patterns.decorator;

/**
 * Some components 2. It calls parent's componentFunction and add some line to it.
 * Note that it takes parent as argument in constructor. This component can either be {@link Decorator} or another {@link Component}
 */
public class ConcreteComponent2 implements Component {

    Component parrent;

    public ConcreteComponent2(Component parent) {
        this.parrent = parent;
    }

    @Override
    public void componentFunction() {
        parrent.componentFunction();
        System.out.println("+ Component 2");
    }
}
