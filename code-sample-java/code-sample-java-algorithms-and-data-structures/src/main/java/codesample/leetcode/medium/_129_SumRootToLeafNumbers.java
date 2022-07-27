package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 129. Sum Root to Leaf Numbers — https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * <p>
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * <p>
 * Each root-to-leaf path in the tree represents a number.
 * <p>
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * A leaf node is a node with no children.
 */
public class _129_SumRootToLeafNumbers {


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

    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    public int sumNumbers(TreeNode node, int pathSum) {
        if (node == null) {
            return 0;
        }

        pathSum = pathSum * 10 + node.val;

        if (node.left == null && node.right == null) {
            return pathSum;
        }

        return sumNumbers(node.left, pathSum)
            + sumNumbers(node.right, pathSum);
    }
}
