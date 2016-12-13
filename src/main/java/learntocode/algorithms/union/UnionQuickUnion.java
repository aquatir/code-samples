
package learntocode.algorithms.union;

import java.util.ArrayList;

/**
 * This is a Union implementation with quick 'union' operation.
 * Union (node1, node2). Takes uo to n
 * Connected (node1, node2). Takes up to n
 * This is not the implementation you should be using a lot. Should only
 * be used as a test subject when learning algorithms
 *
 * @author Pavel Bukhmatov (buhmatov@gmail.com; github.com/aquatir)
 */
public class UnionQuickUnion {
    ArrayList<Integer> elements;

    /**
     * Test method to fill array with integers
     *
     * @param maxNumber - number of integer in array
     */
    private void initiateElements(int maxNumber) {
        for (int i = 0; i < maxNumber; ++i) {
            elements.add(i);
        }
    }

    /**
     * Get index of root for current tree
     *
     * @param a - index of current elements
     * @return index of root
     */
    int root(int a) {
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
        elements.set(rootA, rootB);
    }

    /**
     * Creates new union with none elements being connected.
     * Should be updated to work with any kind of arrayList...?
     *
     * @param n
     */
    UnionQuickUnion(int n) {
        this.elements = new ArrayList<Integer>(n);
        initiateElements(n);
    }
}
   
