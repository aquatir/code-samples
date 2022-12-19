package codesample.leetcode.easy;

import java.util.LinkedList;

/**
 * 226. Invert Binary Tree — https://leetcode.com/problems/invert-binary-tree/description/
 * Given the root of a binary tree, invert the tree, and return its root.
 */
public class _226_InvertBinaryTree_iterative {
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
        public TreeNode invertTree(TreeNode root) {
            // put nodes in queue and rotate the elements

            if (root != null) {

                var queue = new LinkedList<TreeNode>();
                queue.push(root);

                while (!queue.isEmpty()) {
                    var currentNote = queue.poll();

                    var tmp = currentNote.left;
                    currentNote.left = currentNote.right;
                    currentNote.right = tmp;

                    if (currentNote.left != null) {
                        queue.add(currentNote.left);
                    }
                    if (currentNote.right != null) {
                        queue.add(currentNote.right);
                    }
                }

            }

            return root;
        }
    }
}
