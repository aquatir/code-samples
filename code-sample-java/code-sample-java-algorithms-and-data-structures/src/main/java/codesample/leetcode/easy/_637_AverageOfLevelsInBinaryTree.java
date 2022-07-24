package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 637. Average of Levels in Binary Tree — https://leetcode.com/problems/average-of-levels-in-binary-tree/
 * <p>
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
 * Answers within 10-5 of the actual answer will be accepted.
 */
public class _637_AverageOfLevelsInBinaryTree {


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


    public List<Double> averageOfLevels(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) {
            return new ArrayList<Double>();
        }

        int level = 0;
        levelOrder(root, level, result);

        List<Double> averages = new ArrayList<>();

        for (List<Integer> subList : result) {
            long sum = 0;
            long total = subList.size();
            for (Integer value : subList) {
                sum += value;
            }
            double average = (double) sum / total;
            averages.add(average);
        }

        return averages;
    }

    // add current value;
    // traverse left with level + 1
    // traverse right with level + 1

    // this approach is ineffective tbh, because we do construct a result
    // the normal BFS with queue will be much better here like in _107_BinaryTreeLevelOrderTraversalII
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
