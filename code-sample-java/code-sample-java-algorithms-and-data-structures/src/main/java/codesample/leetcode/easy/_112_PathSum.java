package codesample.leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/path-sum/
 * <p>
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * <p>
 * A leaf is a node with no children.
 */
public class _112_PathSum {


    public static class TreeNode {
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


    // recursive bottom-up
//    public boolean hasPathSum(TreeNode root, int targetSum) {
//
//        if (root == null) {
//            return false;
//        }
//
//        if (root.val == targetSum && root.left == null && root.right == null) {
//            return true;
//        }
//
//        return hasPathSum(root.left, targetSum - root.val)
//            || hasPathSum(root.right, targetSum - root.val);
//    }

    // recursive top down
    private boolean found = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        hasPathSum(root, targetSum, 0);

        return found;
    }

    public void hasPathSum(TreeNode root, int targetSum, int curSum) {
        if (root == null) {
            return;
        }
        // reached the end of the tree
        if (root.left == null && root.right == null && targetSum == (curSum + root.val)) {
            found = true;
            return;
        }

        // try to find sum in the left & right subtrees
        hasPathSum(root.left, targetSum, curSum + root.val);
        hasPathSum(root.right, targetSum, curSum + root.val);
    }

    public static void main(String[] args) {
        var s = new _112_PathSum();

        var root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(s.hasPathSum(root, 1)); // expected: false

        var otherRoot = new TreeNode(1);
        otherRoot.left = new TreeNode(2);
        otherRoot.right = new TreeNode(3);
        System.out.println(s.hasPathSum(root, 5)); // expected: false
    }

}
