package codesample.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. Minimum Depth of Binary Tree — https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * <p>
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 */
public class _111_MinimumDepthOfBinaryTree {

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

    public int minDepth(TreeNode root) {
        // BFS (Queue)
        // When a leaf node w/o children is found => return max depth
        // cound current depth

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int maxDepth = 0;

        //  1
        // / \
        // 2

        while (!queue.isEmpty()) {
            maxDepth++;
            int nodesOnThisLevel = queue.size();
            for (int i = 0; i < nodesOnThisLevel; i++) {
                TreeNode node = queue.poll();
                // found leaf node
                if (node.left == null && node.right == null) {
                    return maxDepth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return maxDepth;
    }
}
