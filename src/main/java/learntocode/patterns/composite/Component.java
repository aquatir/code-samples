package learntocode.patterns.composite;

/**
 * Abstract class representing both leafs and composites.
 * Composite should implement every method on Component, while leaf can implement only operation throwing
 * UnsupportedOperationException if any other method is called.
 */
public abstract class Component  {
    abstract void addComponents(Component component);
    abstract void remove(Component component);
    abstract Component getChild(int id);
    abstract void operation();
}
