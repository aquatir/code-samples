package codesample.algorithms.union;

public interface Union<T> {

    /**
     * @return true if nodes are connected, false otherwise
     */
    boolean connected(T nodeOne, T nodeTwo);


    /**
     * Creates a union between two nodes
     */
    void union(T nodeOne, T nodeTwo);
}
