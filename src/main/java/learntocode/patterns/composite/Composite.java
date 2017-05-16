package learntocode.patterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite class can store other Components. It implements all Component methods
 */
public class Composite extends Component {

    private ArrayList<Component> components;
    private Component parent;

    public Composite() {
        components = new ArrayList<>();
    }

    @Override
    void operation() {
        System.out.println("Operation on Composite is called");
    }

    @Override
    void addComponent(Component component) {
        if (component.getParent() == null) {
            component.setParent(this);
            components.add(component);
        } else {
            throw new IllegalArgumentException("Component can not have more them one parent. Current parent: " + getParent());
        }
    }

    @Override
    void removeThis() {
        removeAllChildren();
        if (parent != null)
            parent.remove(this);
    }

    @Override
    void remove(Component component) {
        components.remove(component);
    }

    @Override
    void removeAllChildren() {
        for (Component component: components) {
            if (component.canHasChildren())
                component.removeAllChildren();
            else
                components.remove(component);
        }
    }

    @Override
    Component getParent() {
        return parent;
    }

    @Override
    void setParent(Component component) {
        this.parent = component;
    }

    @Override
    List<Component> getChildren() {
        return components;
    }

    @Override
    boolean hasChildren() {
        return !components.isEmpty();
    }

    @Override
    boolean canHasChildren() {
        return true;
    }

}
