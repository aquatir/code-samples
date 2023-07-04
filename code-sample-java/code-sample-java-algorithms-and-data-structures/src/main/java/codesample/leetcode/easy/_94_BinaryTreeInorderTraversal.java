package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Inorder traversal: traverse left, then root, then right of the tree.
 */
public class _94_BinaryTreeInorderTraversal {

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


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    public void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorderTraversal(root.left, result);
        }
        result.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right, result);
        }
    }

    public static void main(String[] args) {
        System.out.println((double) 5 / 2);
    }
}
