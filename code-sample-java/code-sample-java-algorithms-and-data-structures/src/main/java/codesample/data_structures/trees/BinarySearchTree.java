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
public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Adds a node to current binary search tree in a correct place
     */
    public void addNode(int nodeValue) {
        // edge case => no root is available
        if (root == null) {
            root = new TreeNode(nodeValue);
            return;
        }


    }
}
