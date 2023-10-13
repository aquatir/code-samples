package codesample.leetcode.medium;

import java.util.ArrayDeque;

/**
 * 1161. Maximum Level Sum of a Binary Tree — https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/
 */
public class _1161_MaximumLevelSumOfABinaryTree {

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


    public int maxLevelSum(TreeNode root) {
        var curLevel = 1;
        var maxSum = root.val;
        var maxLevel = 1;

        var q = new ArrayDeque<TreeNode>();
        q.addLast(root);

        while (!q.isEmpty()) {
            var levelSum = 0;
            var levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                var node = q.removeFirst();
                levelSum += node.val;

                if (node.left != null) {
                    q.addLast(node.left);
                }

                if (node.right != null) {
                    q.addLast(node.right);
                }
            }

            // only update if stricktly bigger, so that maxLevel is only update if there is a need to update it
            if (levelSum > maxSum) {
                maxSum = levelSum;
                maxLevel = curLevel;
            }
            curLevel++;
        }

        return maxLevel;
    }
}
