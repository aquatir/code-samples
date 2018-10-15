package codesample.algorithms.union;

public interface Union<T> {

    /**
     * @return true if nodes are connected, false otherwise
     */
    boolean connected(Node<T> nodeOne, Node<T> nodeTwo);


    /**
     * Creates a union between two nodes
     */
    void union(Node<T> nodeOne, Node<T> nodeTwo);
}
