package learntocode.patterns.iterator;

/**
 * Defines iterator interface
 * @param <Type> a type used in this iterator
 */
public interface Iterator<Type> {
    Type next();
    boolean hasNext();
}
