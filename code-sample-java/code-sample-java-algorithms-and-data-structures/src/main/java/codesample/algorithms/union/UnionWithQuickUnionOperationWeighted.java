
package codesample.algorithms.union;

import java.util.ArrayList;

/**
 * This is a Union implementation with quick 'union' operation. Union (node1, node2). Takes up to log(n)
 * Connected (node1, node2). Takes up to log(n). This implementation watch for tree size when adding elements making
 * sure that when connectiong 2 trees the bigger one would get connected to smaller one which decrees tree size and
 * improve overall working time
 *
 * */
class UnionWithQuickUnionOperationWeighted {
    private final ArrayList<Integer> elements;
    private final ArrayList<Integer> size;

    /**
     * Creates new union with none elements being connected.
     * Should be updated to work with generic arrayList...?
     *
     * @param n - number of elements in union
     */
    UnionWithQuickUnionOperationWeighted(int n) {
        this.elements = new ArrayList<>(n);
        this.size = new ArrayList<>(n);
        initiateElements(n);
    }

    /**
     * Test method to fill array with integers
     *
     * @param maxNumber - number of integer in array
     */
    private void initiateElements(int maxNumber) {
        for (int i = 0; i < maxNumber; ++i) {
            elements.add(i);
            /* every element has size 1 */
            size.add(1);
        }
    }

    /**
     * Get index of root for current tree
     *
     * @param a - index of current element
     * @return index of root
     */
    private int root(int a) {
        while (elements.get(a) != a) {
            a = elements.get(a);
        }
        return a;
    }

    /**
     * Return true if nodes are connected
     *
     * @param a - first index
     * @param b - second index
     * @return true if nodes are connected, false otherwise
     */
    boolean connected(int a, int b) {
        return root(elements.get(a)) == root(elements.get(b));
    }

    /**
     * Makes a union between two nodes
     *
     * @param a - first index
     * @param b - second index
     * @return true is union was created and false otherwise
     */
    void union(int a, int b) {
        int rootA = root(a);
        int rootB = root(b);
        if (rootA == rootB)
            return;
        int sizeA = size.get(a);
        int sizeB = size.get(b);
        if (sizeA < sizeB) {
            elements.set(rootA, rootB);
            size.set(rootB, sizeB + sizeA);
        } else {
            elements.set(rootB, rootA);
            size.set(rootA, sizeA + sizeB);
        }
    }
}
   
