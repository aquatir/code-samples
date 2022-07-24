package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. Binary Tree Level Order Traversal II — https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * <p>
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
 */
public class _107_BinaryTreeLevelOrderTraversalII {

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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 1. normal traversal from root
        // 2. swap elements

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodesLeft = new LinkedList();

        nodesLeft.offer(root);

        while (!nodesLeft.isEmpty()) {
            int thisLevelSize = nodesLeft.size();
            List<Integer> thisLevelValues = new ArrayList<>();
            for (int i = 0; i < thisLevelSize; i++) {
                TreeNode node = nodesLeft.poll();
                thisLevelValues.add(node.val);

                if (node.left != null) {
                    nodesLeft.offer(node.left);
                }
                if (node.right != null) {
                    nodesLeft.offer(node.right);
                }
            }
            result.add(thisLevelValues);
        }

        int n = result.size();

        for (int i = 0; i < n / 2; i++) {
            List<Integer> tmp = result.get(n - 1 - i);
            result.set(n - 1 - i, result.get(i));
            result.set(i, tmp);
        }

        return result;
    }

}
