package codesample.data_structures.trees;

import java.util.ArrayList;
import java.util.List;

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
        root = put(root, key, value);
    }

    /**
     * Get to the bottom of a tree, find a place to put a new node or update the current node
     * Then traverse recursively back up the tree updating the pointers
     * Most pointers will be updated to the same value thaw was there before, so this approach is less efficient
     * than the iterative where we could update only once. But this approach is more conscience.
     */
    private TreeNode<K, V> put(TreeNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new TreeNode<>(key, value);
        }
        var cmp = key.compareTo(cur.key);
        if (cmp > 0) { // key is larger => search right subtree
            cur.right = put(cur.right, key, value);
        } else if (cmp < 0) { // key is smaller => search left subtree
            cur.left = put(cur.left, key, value);
        } else { // key equals => override value
            cur.val = value;
        }
        return cur;
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

    public List<TreeNode<K, V>> inOrderTraversal() {
        var res = new ArrayList<TreeNode<K, V>>();
        inOrderTraversal(root, res);
        return res;
    }

    private void inOrderTraversal(TreeNode<K, V> cur, List<TreeNode<K, V>> result) {
        if (cur == null) {
            return;
        }
        inOrderTraversal(cur.left, result);
        result.add(cur);
        inOrderTraversal(cur.right, result);
    }

    public static void main(String[] args) {
        var tree = new BinarySearchTree<Integer, Integer>();
        tree.put(2, 2);
        tree.put(1, 1);
        tree.put(3, 3);

        System.out.println(tree.inOrderTraversal());
    }
}
