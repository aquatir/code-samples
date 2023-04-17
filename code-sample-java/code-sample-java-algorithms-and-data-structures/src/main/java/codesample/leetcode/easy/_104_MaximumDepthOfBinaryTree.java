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

    class Solution {

        private int knownMax = 0;

        public int maxDepth(TreeNode root) {
            return dfs(root, 0);
        }

        private int dfs(TreeNode root, int curDepth) {
            if (root == null) {
                return curDepth;
            }
            return Math.max(
                dfs(root.left, curDepth + 1),
                dfs(root.right, curDepth + 1)
            );
        }
    }
}
