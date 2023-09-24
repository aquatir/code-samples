package codesample.leetcode.medium;

/**
 * 1448. Count Good Nodes in Binary Tree — https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/
 */
public class _1448_CountGoodNodesInBinaryTree {

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


    /** approach 1:
     *  from root to this node => nothing is GREATER
     *  can get the answer if we knew at *each node* what the max in the current subtree is
     */

    public int goodNodes(TreeNode root) {
        // from root to this node => nothing is GREATER
        //   can get the answer if we knew at *each node* what the max in the current
        //   subtree is

        return goodNodes(root, root.val);
    }

    private int goodNodes(TreeNode node, int prevMax) {

        if (node == null) {
            return 0;
        }
        var newMax = Math.max(prevMax, node.val);

        var plus = node.val >= newMax ? 1: 0;
        return plus + goodNodes(node.left, newMax) + goodNodes(node.right, newMax);
    }


    /** approach 2: extract result outside the function */
//    int result = 0;
//
//    public int goodNodes(TreeNode root) {
//
//        goodNodes(root, root.val);
//        return result;
//    }
//
//    private void goodNodes(TreeNode node, int maxVal) {
//        if (node.val >= maxVal) {
//            result++;
//        }
//
//        if (node.left != null) {
//            goodNodes(node.left, Math.max(maxVal, node.left.val));
//        }
//        if (node.right != null) {
//            goodNodes(node.right, Math.max(maxVal, node.right.val));
//        }
//    }
}
