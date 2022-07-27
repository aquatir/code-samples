package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. Path Sum II — https://leetcode.com/problems/path-sum-ii/
 * <p>
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 * <p>
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 */
public class _113_PathSumII {

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


    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();

        pathSum(root, targetSum, currentPath, result);
        return result;
    }

    public void pathSum(TreeNode root, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {

        if (root == null) {
            return;
        }

        currentPath.add(root.val);

        if (root.left == null && root.right == null && root.val == targetSum) {
            result.add(new ArrayList<>(currentPath));
        } else {
            pathSum(root.left, targetSum - root.val, currentPath, result);
            pathSum(root.right, targetSum - root.val, currentPath, result);
        }

        currentPath.remove(currentPath.size() - 1);
    }
}
