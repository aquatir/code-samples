package codesample.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 102. Binary Tree Level Order Traversal — https://leetcode.com/problems/binary-tree-level-order-traversal/
 * <p>
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right,
 * level by level).
 */
public class _102_BinaryTreeLevelOrderTraversal {


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
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> result = new ArrayList<List<Integer>>();
//
//        if (root == null) {
//            return result;
//        }
//
//        int level = 0;
//        levelOrder(root, level, result);
//
//        return result;
//    }
//
//    // add current value;
//    // traverse left with level + 1
//    // traverse right with level + 1
//    public void levelOrder(TreeNode node, int level, List<List<Integer>> result) {
//        if (node == null) {
//            return;
//        } else {
//            if (result.size() <= level) {
//                result.add(new ArrayList<Integer>());
//            }
//            result.get(level).add(node.val);
//            levelOrder(node.left, level + 1, result);
//            levelOrder(node.right, level + 1, result);
//        }
//    }

    // iterative
    public List<List<Integer>> levelOrder(TreeNode root) {
        var res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        var curLevelNodes = new ArrayList<TreeNode>();
        curLevelNodes.add(root);

        // can't put 'null' in the queue to mark
        // how can I possibly mark "level"?
        //  one solution -> have a "cur" level LIST, generate another LIST in the end of iteration
        //  no queue needed at all
        //  but will need an array on TreeNodes.
        while (!curLevelNodes.isEmpty()) {
            var curLevelVals = new ArrayList<Integer>();
            var nextLevelNodes = new ArrayList<TreeNode>();

            for (int i = 0; i < curLevelNodes.size(); i++) {
                var node = curLevelNodes.get(i);
                curLevelVals.add(node.val);
                if (node.left != null) {
                    nextLevelNodes.add(node.left);
                }
                if (node.right != null) {
                    nextLevelNodes.add(node.right);
                }
            }
            res.add(curLevelVals);
            curLevelNodes = nextLevelNodes;
        }
        return res;
    }
}
