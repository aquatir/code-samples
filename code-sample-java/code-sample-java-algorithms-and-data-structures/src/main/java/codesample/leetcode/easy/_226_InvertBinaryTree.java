package codesample.leetcode.easy;

/**
 * 226. Invert Binary Tree — https://leetcode.com/problems/invert-binary-tree/description/
 * Given the root of a binary tree, invert the tree, and return its root.
 */
public class _226_InvertBinaryTree {
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


    class Solution {
        public TreeNode invertTree(TreeNode root) {
            // inverse of null is null
            // inverse of tree with root->left-> is root->right->left

            if (root != null) {
                var leftInverse = invertTree(root.left);
                var rightInverse = invertTree(root.right);

                root.left = rightInverse;
                root.right = leftInverse;
            }

            return root;
        }
    }
}
