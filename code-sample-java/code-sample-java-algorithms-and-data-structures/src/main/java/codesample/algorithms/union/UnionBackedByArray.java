package codesample.algorithms.union;


import java.util.ArrayList;
import java.util.Optional;

/**
 * Union implementation backed by array
 */
public abstract class UnionBackedByArray<T> implements Union<T> {

    private ArrayList<T> unionList;

    @Override
    public abstract boolean connected(T valueOne, T valueTwo);
    @Override
    public abstract void union(T valueOne, T valueTwo);

    public final boolean add(T newValue) {
        return unionList.add(newValue);
    }

    public final Optional<T> get(T value) {
        int index = unionList.indexOf(value);
        if (index != -1) {
            return Optional.of(unionList.get(index));
        }

        return Optional.empty();
    }

    public final int size() {
        return unionList.size();
    }

    public final T set(int index, T value) {
        return unionList.set(index, value);
    }
}
