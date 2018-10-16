
package codesample.algorithms.union;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This is a Union implementation with quick 'connect' operation.
 * Union (node1, node2). Takes uo to n
 * Connected (node1, node2). Takes up to n
 * This is not the implementation you should be using a lot. Should only
 * be used as a test subject when learning algorithms
 */
public class UnionWithQuickUnionOperation<T extends Comparable<T>> implements Union<T>{

    private Map<T, T> map;

    public UnionWithQuickUnionOperation() {
        map = new HashMap<>();
    }

    public UnionWithQuickUnionOperation(Set<T> set) {
        /* Entires refer to themselfs */
        this.map = set.stream()
                .collect(Collectors.toMap(value -> value, value -> value));
    }

    /**
     * Find the root of hierarchy. Root element has a mapping to itself in map
     */
    private T root(T value) {
        var currentValue = value;
        while (map.get(currentValue) != currentValue) {
            currentValue = map.get(currentValue);
        }
        return currentValue;
    }

    @Override
    public boolean areConnected(T nodeOne, T nodeTwo) {
        return root(map.get(nodeOne)).equals(
                root(map.get(nodeTwo)));
    }

    @Override
    public void connect(T nodeOne, T nodeTwo) {
        addUnlinkedNode(nodeOne);
        addUnlinkedNode(nodeTwo);

        T rootLeft = root(nodeOne);
        T rootRight = root(nodeTwo);
        map.put(rootLeft, rootRight);
    }

    @Override
    public void addUnlinkedNode(T node) {
        if (!map.containsKey(node))
            map.put(node, node);
    }

    @Override
    public void removeNode(T node) {

        if (!map.containsKey(node))
            return;

        /* Nodes which should be remapped to other nodes when current node is removed */
        var toBeRemapped = map.keySet().stream()
                .filter(key -> map.get(key).equals(node))
                .collect(Collectors.toList());

        /* If nothing to remap, simply remove the node */
        if (toBeRemapped.isEmpty())
            map.remove(node);

        /* If this node is a root node, all node beneath it should be remapped to a single common node (it can be any
        * node of the list but we simply pick the first one here). This node also become a root node.
        * e.g. for:
        *   A -> B
        *   C -> B
        *   D -> B
        *REMOVE (B)
        *  A -> A
        *  C -> A
        *  D -> A */
        if (map.get(node).equals(node)) {
            var singleCommonNode = toBeRemapped.get(0);
            map.put(singleCommonNode, singleCommonNode);
            toBeRemapped.forEach(value -> map.put(value, singleCommonNode));

        /* If node-to-be-removed is not root, all nodes beneath it should be remapped to the node which node-to-be-removed
        * is mapped to
        * eg for:
        *   A -> B -> C
        *   D -> B -> C
        * REMOVE(B)
        *   A -> C
        *   D -> C */
        } else {
            var upperNode = map.get(node);
            toBeRemapped.forEach(value -> map.put(value, upperNode));
        }

        map.remove(node);
    }
}
   
