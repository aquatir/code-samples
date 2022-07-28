package codesample.leetcode.hard;

/**
 * 124. Binary Tree Maximum Path Sum — https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * <p>
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge
 * connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 */
public class _124_BinaryTreeMaximumPathSum {

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


    int maxSum = Integer.MIN_VALUE;
    int specialMinValue = -2000;

    public int maxPathSum(TreeNode root) {
        sumFromNode(root);
        return maxSum;
    }

    public int sumFromNode(TreeNode node) {
        if (node == null) {
            return specialMinValue; // because we know that -1000 <= Node.val <= 1000
        }

        int leftSum = sumFromNode(node.left);
        int rightSum = sumFromNode(node.right);

        int returnableMax = specialMinValue; // we can only return sum from Node which includes the current node, so any of the sums including current will suffice
        returnableMax = Math.max(node.val, returnableMax);
        returnableMax = Math.max(node.val + leftSum, returnableMax);
        returnableMax = Math.max(node.val + rightSum, returnableMax);

        int curSum = specialMinValue; // but the max for this particular node can also be either of the sums
        curSum = Math.max(curSum, returnableMax);
        curSum = Math.max(leftSum + rightSum + node.val, curSum);
        curSum = Math.max(leftSum, curSum);
        curSum = Math.max(rightSum, curSum);

        maxSum = Math.max(curSum, maxSum);

        return returnableMax;
    }

    public static void main(String[] args) {
        var s = new _124_BinaryTreeMaximumPathSum();

//        var root = new TreeNode(1);
//        root.right = new TreeNode(-3);
//        root.right.right = new TreeNode(-2);
//        root.left = new TreeNode(-2);
//        root.left.right = new TreeNode(3);
//        root.left.left = new TreeNode(1);
//        root.left.left.left = new TreeNode(-1); // expected = 3
// System.out.println(s.maxPathSum(root)); // expected = 3

        var root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        System.out.println(s.maxPathSum(root)); // expected 48
    }
}
