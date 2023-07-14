package codesample.leetcode.medium;

public class _250_CountUnivalueSubtrees {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    private int totalUniValue = 0;

    public int countUnivalSubtrees(TreeNode root) {
        // we only increment number of uni-values at the leaf node
        // when going down the tree, see if both children are uni-value.
        // If yes, check if their value is equal to current, if yes, increment, if not, don't increment

        traverse(root);
        return totalUniValue;
    }

    private boolean traverse(TreeNode root) {
        if (root == null) {
            return true;
        }

        var leftIsUni = traverse(root.left);
        var rightIsUni = traverse(root.right);

        // If both the children form uni-value subtrees, we compare the value of
        // chidren's node with the node value.
        if (leftIsUni && rightIsUni) {
            if (root.left != null && root.left.val != root.val) {
                return false;
            }
            if (root.right != null && root.right.val != root.val) {
                return false;
            }
            totalUniValue++;
            return true;
        }
        // Else if any of the child does not form a uni-value subtree, the subtree
        // rooted at node cannot be a uni-value subtree.
        return false;
    }
}
