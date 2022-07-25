package codesample.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 993. Cousins in Binary Tree — https://leetcode.com/problems/cousins-in-binary-tree/
 * <p>
 * Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return
 * true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
 * <p>
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 * <p>
 * Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.
 */
public class _993_CousinsInBinaryTree {


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


    public boolean isCousins(TreeNode root, int x, int y) {

        // try BFS?

        if (root == null) {
            return false;
        }

        boolean xFound = false;
        boolean yFound = false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            xFound = false;
            yFound = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.val == x) {
                    xFound = true;
                }
                if (node.val == y) {
                    yFound = true;
                }

                if (node.left != null && node.right != null) {
                    if (node.left.val == x && node.right.val == y
                        || node.left.val == y && node.right.val == x) {
                        return false; // equal non-siblings
                    }
                }

                // if one found after another => they are bros, not siblings
                if (xFound && yFound) {
                    return true;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return false;
    }
}
