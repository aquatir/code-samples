package learntocode.patterns.composite;

import java.util.List;

/**
 * Abstract class representing both leafs and composites.
 * {@link learntocode.patterns.composite.Composite} should implement every method on Component
 * {@link learntocode.patterns.composite.Leaf} should implement some methods as you see adequate. For example,
 * leaf can not has children, so a call to removeAllChildren() might throw an exception or simply do nothing.
 * The same goes for hasChildren as leafs can never have children leafs.
 *<br><br>
 *
 * NOTE1: Leaf should implement canHasChildren() to always return false; <br>
 * NOTE2: Method remove(Component component) should not actually be called on leaf. You should only call removeSelf() when removing leaf. <br>
 *  This is probably a bad design but I can't think of a better solution right now. <br>
 * NOTE3: This composition implementation is NOT thread-safe
 *
 */
public abstract class Component {

    private Component parent;

    final Component getParent() {
        return parent;
    }
    final void setParent(Component component) {
        this.parent = component;
    }

    abstract void addComponent(Component component);

    abstract void removeSelf();
    abstract void remove(Component component);
    abstract void removeAllChildren();
    abstract String getName();



    abstract List<Component> getChildren();
    abstract boolean hasChildren();
    abstract boolean canHasChildren();
    abstract void operation();
}
