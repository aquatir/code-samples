package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal — https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 */
public class _105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

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


    int preorderNodeIndex;
    int[] preorder;
    int[] inorder;
    Map<Integer, Integer> inorderValueToIndexInArray;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // preorder => root => left => right
        // inorder  => left => root => right

        // The build process will be:
        //  1. pick the first node of preorder -> it will be root of a subtree. In the beginning it's THE root
        //  2. Find this node in inorder traversal. It's in-order, so the root will split the inorder array into
        // left part and right part.
        //  3. construct current node from the root — from postorder value
        //  4. run the same algorithm for the left & and the right part of the tree
        // NOTE: to detect NULL value, we pass in the helper function the boundaries where the left & right tree could be
        // in in-order traversal.

        this.preorder = preorder;
        this.inorder = inorder;
        preorderNodeIndex = 0;
        inorderValueToIndexInArray = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderValueToIndexInArray.put(inorder[i], i);
        }

        return helper(0, preorder.length - 1);
    }

    private TreeNode helper(int leftIndexBound, int rightIndexBound) {
        // if there are no elements to construct the tree
        if (leftIndexBound > rightIndexBound) return null;

        // select the preorderIndex element as the root and increment it
        int rootValue = preorder[preorderNodeIndex];
        TreeNode root = new TreeNode(rootValue);
        preorderNodeIndex++;

        // build left and right subtree
        // excluding element from the middle, because we have just used it to build a tree
        root.left = helper(leftIndexBound, inorderValueToIndexInArray.get(rootValue) - 1);
        root.right = helper(inorderValueToIndexInArray.get(rootValue) + 1, rightIndexBound);
        return root;
    }
}
