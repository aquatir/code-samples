package patterns.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Composite class can store other Components. It implements all Component methods.
 * It may still contains some bugs as of current version. BE AWARE AND TEST BEFORE USE!
 * or wait till I add up test methods.
 */
class Composite extends Component {

    private final ArrayList<Component> components;
    private final String name;

    public Composite(String name) {
        components = new ArrayList<>();
        this.name = name;
    }

    @Override
    void operation() {
        for (Component component: components) {
            component.operation();
        }
        System.out.println("Called operation on Composite " + name);
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
    void removeSelf() {
        removeAllChildren();
        Component parent = getParent();
        if (parent != null)
            parent.remove(this);
    }

    @Override
    void remove(Component component) {
        components.remove(component);
    }

    @Override
    void removeAllChildren() {
        for (Iterator<Component> itr = components.iterator(); itr.hasNext();) {
            Component component = itr.next();

            if (component.canHasChildren()){
                component.removeAllChildren();
            } else {
                component.setParent(null);
                itr.remove();
            }
        }
    }

    @Override
    String getName() {
        return name;
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
