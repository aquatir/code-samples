package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 872. Leaf-Similar Trees — https://leetcode.com/problems/leaf-similar-trees/description/
 */
public class _872_LeafSimilarTrees {


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


    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        return leafs(root1).equals(leafs(root2));
    }

    // return a list of all leafs
    private List<Integer> leafs(TreeNode root) {
        var leafs = new ArrayList<Integer>();

        leafs(root, leafs);
        return leafs;
    }

    private void leafs(TreeNode root, List<Integer> leafs) {
        // base case
        if (root.left == null && root.right == null) {
            leafs.add(root.val);
        }

        // always look on the left first, before looking on the right to add
        // leafs in the correct order
        if (root.left != null) {
            leafs(root.left, leafs);
        }
        if (root.right != null) {
            leafs(root.right, leafs);
        }
    }
}
