
package codesample.algorithms.union;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This is a Union implementation with quick 'connect' operation.
 *
 * connect (node1, node2). Takes up to 0(log(n))
 * areConnected (node1, node2). Takes up to 0(log(n)).
 * removeNode(node). Takes up to 0(log(n))
 *
 * This implementation watch for tree sizesMap when adding elements making
 * sure that when connecting 2 trees the smaller one would get connected to bigger one which decrees overall tree size.
 *
 * The tree size will not get increased unless two trees with the same size are getting connected. If that's the case
 * */
public class UnionWithQuickUnionOperationWeighted<T extends Comparable<T>> implements Union<T>{

    private Map<T, T> valuesMap;
    private Map<T, Integer> sizesMap;


    public UnionWithQuickUnionOperationWeighted() {
        valuesMap = new HashMap<>();
        sizesMap = new HashMap<>();
    }

    public UnionWithQuickUnionOperationWeighted(Set<T> set) {
        /* Initially all entices are roots thus they refer to themselves */
        this.valuesMap = set.stream()
                .collect(Collectors.toMap(value -> value, value -> value));

        // By default sizesMap of each node is 1
        sizesMap = set.stream().collect(
                Collectors.toMap(value -> value, value -> 1));
    }

    /**
     * Find the root of hierarchy. Root element has a mapping to itself in valuesMap
     */
    private T root(T value) {
        var currentValue = value;
        while (valuesMap.get(currentValue) != currentValue) {
            currentValue = valuesMap.get(currentValue);
        }
        return currentValue;
    }

    @Override
    public boolean areConnected(T nodeOne, T nodeTwo) {
        var rootLeft =  root(nodeOne);
        var rootRight = root(nodeTwo);

        if (rootLeft == null || rootRight == null)
            return false;
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

        if (rootLeft.equals(rootRight))
            return;

        var sizeLeft = sizesMap.get(rootLeft);
        var sizeRight = sizesMap.get(rootRight);

        if (sizeLeft < sizeRight) {
            valuesMap.put(rootLeft, rootRight);
        } else if (sizeLeft > sizeRight){
            valuesMap.put(rootRight, rootLeft);
        } else {
            valuesMap.put(rootRight, rootLeft);
            sizesMap.put(rootLeft, sizeLeft + 1);
        }
    }

    @Override
    public void addUnlinkedNode(T node) {
        if (!valuesMap.containsKey(node)) {
            valuesMap.put(node, node);
            sizesMap.put(node, 1);
        }
    }

    @Override
    public void removeNode(T node) {

        if (!valuesMap.containsKey(node))
            return;

        /* Nodes which should be remapped to other nodes when current node is removed */
        var toBeRemapped = valuesMap.keySet().stream()
                .filter(key -> valuesMap.get(key).equals(node))
                .collect(Collectors.toList());

        /* If nothing to remap, simply remove the node */
        if (toBeRemapped.isEmpty()) {
            valuesMap.remove(node);
            sizesMap.remove(node);
            return;
        }

        /* If this node is a root node, all node beneath it should be remapped to a single common node (it can be any
         * node of the list but we simply pick the first one here). This node also become a root node.
         * e.g. for:
         *   A -> B
         *   C -> B
         *   D -> B
         * REMOVE (B)
         *  A -> A
         *  C -> A
         *  D -> A */
        if (valuesMap.get(node).equals(node)) {
            var singleCommonNode = toBeRemapped.get(0);
            valuesMap.put(singleCommonNode, singleCommonNode);
            sizesMap.put(singleCommonNode, 1);
            toBeRemapped.forEach(value -> valuesMap.put(value, singleCommonNode));
            valuesMap.remove(node);
            sizesMap.remove(node);

            /* If node-to-be-removed is not root, all nodes beneath it should be remapped to the node which node-to-be-removed
             * is mapped to
             * eg for:
             *   A -> B -> C
             *   D -> B -> C
             * REMOVE(B)
             *   A -> C
             *   D -> C */
        } else {
            var upperNode = valuesMap.get(node);
            toBeRemapped.forEach(value -> {
                valuesMap.put(value, upperNode);
                sizesMap.put(value, sizesMap.get(value) - 1);
            });
            valuesMap.remove(node);
            sizesMap.remove(node);
        }
    }
}
   
