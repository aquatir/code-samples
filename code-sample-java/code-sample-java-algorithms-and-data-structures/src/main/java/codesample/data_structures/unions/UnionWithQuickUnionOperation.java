package codesample.data_structures.unions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This is a Union implementation with quick 'connect' operation.
 * <ul>
 *  <li> {@code connect (node1, node2)}. Takes up to 0(n)
 *  <li> {@code areConnected (node1, node2)}. Takes up 0(n)
 *  <li> {@code removeNode(node)}. Takes up to 0(n)
 * </ul>
 * <p>
 * This is a naive implementation. A lot of improvement can be applied to it.
 * See test in test/java/algorithms/union/UnionWithQuickUnionOperationTest
 */
public class UnionWithQuickUnionOperation<T extends Comparable<T>> implements Union<T> {

    private Map<T, T> map;

    public UnionWithQuickUnionOperation() {
        map = new HashMap<>();
    }

    public UnionWithQuickUnionOperation(Set<T> set) {
        // Entries refer to themselves initially
        // with updates, multiple entries will start referring same elements creating unions
        this.map = set.stream().collect(Collectors.toMap(value -> value, value -> value));
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
        var rootLeft = root(nodeOne);
        var rootRight = root(nodeTwo);

        if (rootLeft == null || rootRight == null) return false;
        else {
            return rootLeft.equals(rootRight);
        }
    }


    @Override
    public void connect(T nodeOne, T nodeTwo) {
        addUnlinkedNode(nodeOne);
        addUnlinkedNode(nodeTwo);

        T rootLeft = root(nodeOne);
        T rootRight = root(nodeTwo);

        // two nodes are connected already
        if (rootLeft.equals(rootRight)) {
            return;
        }

        // make one root link to another root, connecting two unions
        map.put(rootLeft, rootRight);
    }

    /**
     * Add a new node to a union, not connected to any other node
     */
    @Override
    public void addUnlinkedNode(T node) {
        if (!map.containsKey(node)) {
            map.put(node, node);
        }
    }

    @Override
    public void removeNode(T node) {

        if (!map.containsKey(node)) {
            return;
        }

        /* Nodes which should be remapped to other nodes when current node is removed */
        var toBeRemapped = map.keySet().stream().filter(key -> map.get(key).equals(node)).toList();

        /* If nothing to remap, simply remove the node */
        if (toBeRemapped.isEmpty()) {
            map.remove(node);
            return;
        }

        if (map.get(node).equals(node)) { // if root node
            /* If this node is a root node, all node beneath it should be remapped to a single common node (it can be any
             * node of the list, but we simply pick the first one here). This node also become a root node.
             * e.g. for:
             *   A -> B
             *   C -> B
             *   D -> B
             * REMOVE (B)
             *  A -> A
             *  C -> A
             *  D -> A */
            var singleCommonNode = toBeRemapped.get(0);
            map.put(singleCommonNode, singleCommonNode);
            toBeRemapped.forEach(value -> map.put(value, singleCommonNode));
            map.remove(node);

        } else { // if not root node
            /* If node-to-be-removed is not root, all nodes beneath it should be remapped to the node which node-to-be-removed
             * is mapped to
             * e.g. for:
             *   A -> B -> C
             *   D -> B -> C
             * REMOVE(B)
             *   A -> C
             *   D -> C */
            var upperNode = map.get(node);
            toBeRemapped.forEach(value -> map.put(value, upperNode));
            map.remove(node);
        }
    }
}
   
