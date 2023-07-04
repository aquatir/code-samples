package codesample.leetcode.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 144. Binary Tree Preorder Traversal — https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 */
public class _144_BinaryTreePreorderTraversal {

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

        // recursive
//        public List<Integer> preorderTraversal(TreeNode root) {
//            var list = new ArrayList<Integer>();
//            preorderTraversal(root, list);
//            return list;
//        }
//
//        public void preorderTraversal(TreeNode root, List<Integer> list) {
//            if (root == null) {
//                return;
//            }
//            list.add(root.val);
//
//            preorderTraversal(root.left, list);
//            preorderTraversal(root.right, list);
//        }

        // iterative
        public List<Integer> preorderTraversal(TreeNode root) {
            var answer = new ArrayList<Integer>();
            var stack = new ArrayDeque<TreeNode>();
            if (root == null) {
                return answer;
            }
            stack.push(root);

            // Note that we add currNode's right child to the stack first.
            while (!stack.isEmpty()) {
                var cur = stack.pop();
                answer.add(cur.val);
                // first add right child and left on top of is to pop left faster
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }

            return answer;
        }
    }

}
