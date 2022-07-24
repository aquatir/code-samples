package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 103. Binary Tree Zigzag Level Order Traversal — https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to
 * right, then right to left for the next level and alternate between).
 */
public class _103_BinaryTreeZigzagLevelOrderTraversal {

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


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) {
            return result;
        }

        int level = 0;
        levelOrder(root, level, result);

        // swap to make zig-zag form
        boolean fromLeft = true;
        for (List<Integer> curLevel : result) {
            if (fromLeft) {
                fromLeft = !fromLeft;
            } else {
                // swap
                int n = curLevel.size();
                for (int i = 0; i < n / 2; i++) {
                    int tmp = curLevel.get(n - 1 - i);
                    curLevel.set(n - 1 - i, curLevel.get(i));
                    curLevel.set(i, tmp);
                }
                fromLeft = !fromLeft;
            }
        }

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
