package learntocode.patterns.composite;

import java.util.List;

/**
 * Abstract class representing both leafs and composites.
 * {@link learntocode.patterns.composite.Composite} should implement every method on Component
 * {@link learntocode.patterns.composite.Leaf} should implement some methods as you see adequate. For example,
 * leaf can not has children, so a call to removeAllChildren() might throw an exception or simply do nothing.
 * The same goes for hasChildren as leafs can never have children
 *
 * NOTE: Leaf should implement canHasChildren() to always return false;
 * NOTE2: method remove(Component component) should not actually be called on leaf. You should only call removeThis() when removing
 * leafs. This is probably a bad design but I can't think of a better solution right now.
 */
public abstract class Component  {
    abstract void addComponent(Component component);

    abstract void removeThis();
    abstract void remove(Component component);
    abstract void removeAllChildren();
    abstract String getName();

    abstract Component getParent();
    abstract void setParent(Component component);

    abstract List<Component> getChildren();
    abstract boolean hasChildren();
    abstract boolean canHasChildren();
    abstract void operation();
}
