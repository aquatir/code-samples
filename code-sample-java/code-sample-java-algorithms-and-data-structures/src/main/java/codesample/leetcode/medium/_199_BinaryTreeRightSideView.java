package codesample.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. Binary Tree Right Side View — https://leetcode.com/problems/binary-tree-right-side-view/
 * <p>
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 */
public class _199_BinaryTreeRightSideView {


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


    // approach 1: with LinkedList as queue
//    public List<Integer> rightSideView(TreeNode root) {
//        // bfs
//        // add last value on each cycle to the list
//
//        List<Integer> result = new ArrayList<>();
//        if (root == null) {
//            return result;
//        }
//
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            TreeNode last = null;
//            for (int i = 0; i < size; i++) {
//                last = queue.poll();
//                if (last.left != null) {
//                    queue.offer(last.left);
//                }
//                if (last.right != null) {
//                    queue.offer(last.right);
//                }
//            }
//            result.add(last.val);
//        }
//
//        return result;
//    }

    // approach 2: with array deque
    public List<Integer> rightSideView(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        var res = new ArrayList<Integer>();
        var q = new ArrayDeque<TreeNode>();

        q.addLast(root);

        while (!q.isEmpty()) {

            // loop over current level;
            var size = q.size();
            var lastIndex = size - 1;

            for (int i = 0; i < size; i++) {
                var node = q.removeFirst();

                if (node.left != null) {
                    q.addLast(node.left);
                }
                if (node.right != null) {
                    q.addLast(node.right);
                }

                if (i == lastIndex) {
                    res.add(node.val);
                }
            }
        }

        return res;
    }

}
