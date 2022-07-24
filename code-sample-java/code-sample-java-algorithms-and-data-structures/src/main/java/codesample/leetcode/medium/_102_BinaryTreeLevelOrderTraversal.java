package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 102. Binary Tree Level Order Traversal — https://leetcode.com/problems/binary-tree-level-order-traversal/
 * <p>
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right,
 * level by level).
 */
public class _102_BinaryTreeLevelOrderTraversal {


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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) {
            return result;
        }

        int level = 0;
        levelOrder(root, level, result);

        return result;
    }

    // add current value;
    // traverse left with level + 1
    // traverse right with level + 1
    public void levelOrder(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) {
            return;
        } else {
            if (result.size() <= level) {
                result.add(new ArrayList<Integer>());
            }
            result.get(level).add(node.val);
            levelOrder(node.left, level + 1, result);
            levelOrder(node.right, level + 1, result);
        }
    }
}
