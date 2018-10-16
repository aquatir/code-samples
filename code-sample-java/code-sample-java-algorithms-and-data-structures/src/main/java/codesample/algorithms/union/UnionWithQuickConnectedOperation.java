
package codesample.algorithms.union;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * This is a Union implementation with quick 'areConnected' operation.
 * connect (node1, node2). Takes O(n)
 * areConnected (node1, node2). Takes 0(1)
 */
public class UnionWithQuickConnectedOperation<T extends Comparable<T>> implements Union<T> {

    private Map<T, Integer> map;
    private Integer nextSetIndex;

    public UnionWithQuickConnectedOperation() {
        map = new HashMap<>();
        nextSetIndex = 0;
    }

    public UnionWithQuickConnectedOperation(Set<T> set) {
        var list = new ArrayList<>(set);
        this.map = new HashMap<>(list.size());
        IntStream.range(0, list.size())
                .forEach(i -> map.put(list.get(i), i));

        this.nextSetIndex = list.size();
    }

    @Override
    public boolean areConnected(T valueOne, T valueTwo) {
        return getIndexByValue(valueOne).equals(getIndexByValue(valueTwo));
    }

    @Override
    public void connect(T valueOne, T valueTwo) {
        addUnlinkedNode(valueOne);
        addUnlinkedNode(valueTwo);
        Integer oldIndex = getIndexByValue(valueOne);
        Integer newIndex = getIndexByValue(valueTwo);

        remapOldIndexToNewIndex(oldIndex, newIndex);
    }

    @Override
    public void addUnlinkedNode(T node) {
        if (!map.containsKey(node)) {
            map.put(node, nextSetIndex);
            nextSetIndex++;
        }
    }

    @Override
    public void removeNode(T node) {
        map.remove(node);
    }

    private Integer getIndexByValue(T value) {
        return map.get(value);
    }

    private void remapOldIndexToNewIndex(Integer oldIndex, Integer newIndex) {
        map.entrySet().stream()
                .filter(entry -> entry.getValue().equals(oldIndex))
                .forEach(entry -> map.put(entry.getKey(), newIndex));
    }
}