package learntocode.patterns.composite;

import java.util.Collections;
import java.util.List;

/**
 * Leaf class extends component but does not implement all of the operations
 */
public class Leaf extends Component {

    private Component parent;

    public Leaf() {
    }

    @Override
    void operation() {
        System.out.println("Operation of leaf is called");
    }

    @Override
    void addComponent(Component component) throws  UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot remove leaf component from it's own reference");
    }

    @Override
    void removeThis() {
        remove(this);
    }

    @Override
    void remove(Component component) {
        parent.remove(component);
    }


    @Override
    void removeAllChildren() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Can not make a call to removeAllChildren() on leaf element");
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
        return Collections.emptyList();
    }

    @Override
    boolean hasChildren() {
        return false;
    }

    @Override
    boolean canHasChildren() {
        return false;
    }
}
