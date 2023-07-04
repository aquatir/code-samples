package codesample.leetcode.easy;

import java.util.ArrayDeque;
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


    // recursive
//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
//        inorderTraversal(root, result);
//        return result;
//    }
//
//    public void inorderTraversal(TreeNode root, List<Integer> result) {
//        if (root == null) {
//            return;
//        }
//        if (root.left != null) {
//            inorderTraversal(root.left, result);
//        }
//        result.add(root.val);
//        if (root.right != null) {
//            inorderTraversal(root.right, result);
//        }
//    }

    // iterative
    public List<Integer> inorderTraversal(TreeNode root) {
        var answer = new ArrayList<Integer>();
        var stack = new ArrayDeque<TreeNode>();
        var cur = root;
        while(cur != null || !stack.isEmpty()) {
            // push all the left nodes first
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // add cur node
            cur = stack.pop();
            answer.add(cur.val);
            // switch to right side. If it has left children, the condition above will become true, so we'll push them
            cur = cur.right;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println((double) 5 / 2);
    }
}
