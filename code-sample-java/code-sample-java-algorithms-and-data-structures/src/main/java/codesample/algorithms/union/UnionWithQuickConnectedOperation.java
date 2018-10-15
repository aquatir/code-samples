
package codesample.algorithms.union;

import java.util.ArrayList;

/**
 * This is a Union implementation with quick 'connected' operation.
 * Union (node1, node2). Takes O(n)
 * Connected (node1, node2). Takes 0(1)
 */
class UnionWithQuickConnectedOperation<T> extends UnionBackedByArray<T> {


    @Override
    public boolean connected(T valueOne, T valueTwo) {
        return get(valueOne).equals(get(valueTwo));
    }

    @Override
    public void union(T valueOne, T valueTwo) {
        int indexOne =
    }

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
    UnionWithQuickConnectedOperation(int n) {
        this.arrList = new ArrayList<>(n);
        fillArrayList(n);
    }



}