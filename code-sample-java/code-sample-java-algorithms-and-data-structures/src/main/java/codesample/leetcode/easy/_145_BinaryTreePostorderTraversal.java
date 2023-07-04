package codesample.leetcode.easy;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 145. Binary Tree Postorder Traversal — https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 */
public class _145_BinaryTreePostorderTraversal {

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
//    public List<Integer> postorderTraversal(TreeNode root) {
//        var res = new ArrayList<Integer>();
//        postorderTraversal(root, res);
//        return res;
//    }

    public void postorderTraversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        postorderTraversal(root.left, res);
        postorderTraversal(root.right, res);

        res.add(root.val);
    }

    // iterative
    public List<Integer> postorderTraversal(TreeNode root) {
        var res = new ArrayList<Integer>();
        var deque = new ArrayDeque<TreeNode>();

        deque.push(root);
        var cur = root;
        while (cur != null || !deque.isEmpty()) {
            // first push everything on the left to stack
            while (cur != null) {
                deque.push(cur);
                cur = cur.left;
            }
            // add cur node
            cur = deque.pop();
            // switch to right side. If it has left children, the condition above will become truem so we'll push them
            cur = cur.right;


        }

        return res;
    }
}
