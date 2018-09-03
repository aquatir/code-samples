package patterns.composite;

import java.util.Collections;
import java.util.List;

/**
 * Leaf class extends component but does not implement all of the operations
 */
class Leaf extends Component {

    private final String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    void operation() {
        System.out.println("Operation is called of leaf " + name);
    }

    @Override
    void addComponent(Component component) throws  UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot remove leaf component from it's own reference");
    }

    @Override
    void removeSelf() {
        remove(this);
    }

    @Override
    void remove(Component component) {
        getParent().remove(component);
    }


    @Override
    void removeAllChildren() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Can not make a call to removeAllChildren() on leaf element");
    }

    @Override
    String getName() {
        return name;
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
