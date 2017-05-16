package learntocode.patterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite class can store other Components. It implements all Component methods
 */
public class Composite extends Component {

    private ArrayList<Component> components;


    public Composite() {
        components = new ArrayList<Component>();
    }

    public ArrayList<Component> getAllComponents() {
        return components;
    }

    @Override
    void operation() {
        System.out.println("Operation of Composite is called");
    }

    @Override
    void addComponents(Component component) {
        components.add(component);
    }

    @Override
    void remove(Component component) {
        components.remove(component);
    }

    @Override
    List<Component> getChildren() {
        return null;
    }

}
