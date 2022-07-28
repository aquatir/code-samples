package codesample.leetcode.easy;

/**
 * 543. Diameter of Binary Tree — https://leetcode.com/problems/diameter-of-binary-tree/
 * <p>
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * <p>
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * <p>
 * The length of a path between two nodes is represented by the number of edges between them.
 */
public class _543_DiameterOfBinaryTree {

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


    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        lengthFrom(root);
        return maxDiameter;
    }

    public int lengthFrom(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSize = lengthFrom(node.left);
        int rightSize = lengthFrom(node.right);

        maxDiameter = Math.max(leftSize + rightSize, maxDiameter);

        return Math.max(leftSize, rightSize) + 1;
    }
}
