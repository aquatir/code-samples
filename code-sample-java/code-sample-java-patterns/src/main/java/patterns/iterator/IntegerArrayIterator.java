package patterns.iterator;

/**
 * Concrete iterator implementations.
 * This should be created in your class to iterate over Integer array like this: {@link IteratorUser}
 */
public class IntegerArrayIterator implements Iterator<Integer> {

    private final Integer[] array;
    private int position;

    public IntegerArrayIterator(Integer[] array) {
        this.array = array;
        position = 0;
    }

    @Override
    public Integer next() {
        ++position;
        return array[position - 1];
    }

    @Override
    public boolean hasNext() {
        return position < array.length;

    }
}
