package codesample.data_structures.trees;

public class TreeNode<K extends Comparable<K>, V> {
    K key;
    V val;
    TreeNode<K, V> left;
    TreeNode<K, V> right;

    TreeNode() {
    }

    TreeNode(K key, V val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
            "key=" + key +
            ", val=" + val +
            '}';
    }
}
