
package learn_to_code.algorithms.union;

import java.util.ArrayList;

/**
 * This is a Union implementation with quick 'connected' operation.
 * Union (node1, node2). Takes O(n)
 * Connected (node1, node2). Takes 0(1)
 *
 * @author Pavel Bukhmatov (buhmatov@gmail.com; github.com/aquatir)
 */
public class UnionQuickSearch {
    ArrayList<Integer> arrList;

    /**
     * Test method to fill array with integers
     *
     * @param maxNumber - number of integer in array
     */
    private void fillArrayList(int maxNumber) {
        for (int i = 0; i < maxNumber; ++i) {
            arrList.add(i);
        }
    }

    /**
     * Return true if nodes are connected
     *
     * @param a - first index
     * @param b - second index
     * @return true if nodes are connected, false otherwise
     */
    boolean connected(int a, int b) {
        return (arrList.get(a).equals(arrList.get(b)));
    }

    /**
     * Makes a union between two nodes
     *
     * @param a - first index
     * @param b - second index
     * @return true is union was created and false otherwise
     */
    void union(int a, int b) {
        int oldSetValue = arrList.get(a);
        int newSetValue = arrList.get(b);

        for (int i = 0; i < arrList.size(); ++i) {
            if (arrList.get(i) == oldSetValue) {
                arrList.set(i, newSetValue);
            }
        }
    }

    /**
     * Creates new union with none elements being connected.
     * Should be updated to work with any kind of arrayList...?
     *
     * @param n
     */
    UnionQuickSearch(int n) {
        this.arrList = new ArrayList<Integer>(n);
        fillArrayList(n);
    }
}