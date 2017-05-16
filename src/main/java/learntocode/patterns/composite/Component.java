package learntocode.patterns.composite;

import java.util.List;

/**
 * Abstract class representing both leafs and composites.
 * Composite should implement every method on Component, while leaf can implement only operation throwing
 * UnsupportedOperationException if any other method is called.
 */
public abstract class Component  {
    abstract void addComponent(Component component);

    abstract void removeThis();
    abstract void remove(Component component);
    abstract void removeAllChildren();

    abstract Component getParent();
    abstract void setParent(Component component);

    abstract List<Component> getChildren();
    abstract boolean hasChildren();
    abstract boolean canHasChildren();
    abstract void operation();
}
