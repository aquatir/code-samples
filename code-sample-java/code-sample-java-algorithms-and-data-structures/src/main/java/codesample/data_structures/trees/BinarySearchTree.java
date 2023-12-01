package codesample.data_structures.trees;

/**
 * Nodes on the left and <= to root
 * Nodes on the right are > root
 * <p>
 * Supports operations :
 * <ul>
 *  <li> add node
 *  <li> delete node
 *  <li> pre-order, in-order, post-order traversal outputs
 * </ul>
 * </p>
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
    private TreeNode<K, V> root;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Adds a node to current binary search tree in a correct place
     */
    public void put(K key, V value) {
    }

    public V get(K key) {
        var cur = root;

        while (cur != null) {
            var cmp = key.compareTo(cur.key);
            if (cmp > 0) { // key is larger => need to search in right subtree
                cur = cur.right;
            } else if (cmp < 0) { // key is smaller => need to search in left subtree
                cur = cur.left;
            } else { // key equals, found result
                return cur.val;
            }
        }
        return null;
    }
}
