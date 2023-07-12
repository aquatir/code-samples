package codesample.leetcode.easy;

/**
 * 104. Maximum Depth of Binary Tree — https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 */
public class _104_MaximumDepthOfBinaryTree {

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

    // top-down
    class Solution {

        private int knownMax = 0;

        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            maxDepth(root, 1);
            return knownMax;
        }

        private void maxDepth(TreeNode root, int curDepth) {
            if (root == null) {
                return;
            }

            knownMax = Math.max(knownMax, curDepth);

            maxDepth(root.left, curDepth + 1);
            maxDepth(root.right, curDepth + 1);
        }
    }

    // bottom-up
//    class Solution {
//
//        public int maxDepth(TreeNode root) {
//            if (root == null) {
//                return 0;
//            }
//
//            var left = maxDepth(root.left);
//            var right = maxDepth(root.right);
//
//            return Math.max(left, right) + 1;
//        }
//    }
}
