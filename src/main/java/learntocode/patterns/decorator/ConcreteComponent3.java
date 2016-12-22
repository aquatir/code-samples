package learntocode.patterns.decorator;

/**
 * Some components 3. It calls parent's componentFunction and add some line to it.
 * Note that it takes parent as argument in constructor. This component can either be {@link Decorator} or another {@link Component}
 */
public class ConcreteComponent3 implements Component {

    Component parrent;

    public ConcreteComponent3(Component parent) {
        this.parrent = parent;
    }

    @Override
    public void componentFunction() {
        parrent.componentFunction();
        System.out.println("+ Component 3");
    }
}
