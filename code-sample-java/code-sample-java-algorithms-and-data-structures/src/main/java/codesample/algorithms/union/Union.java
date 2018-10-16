package codesample.algorithms.union;


public interface Union<T extends Comparable<T>> {

    /**
     * @return true if nodes are areConnected, false otherwise
     */
    boolean areConnected(T nodeOne, T nodeTwo);

    /**
     * Creates a connect between two nodes
     */
    void connect(T nodeOne, T nodeTwo);

    /**
     * Add new node to this union in it's own union (union of size = 1)
     */
    void addUnlinkedNode(T node);

    /**
     * Remove node from this union
     */
    void removeNode(T node);
}
