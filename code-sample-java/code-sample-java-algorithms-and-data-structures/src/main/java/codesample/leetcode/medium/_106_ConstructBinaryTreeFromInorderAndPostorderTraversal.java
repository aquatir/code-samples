package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal — https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 */
public class _106_ConstructBinaryTreeFromInorderAndPostorderTraversal {

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


    int postOrderNodeIndex;
    int[] postorder;
    int[] inorder;
    Map<Integer, Integer> inorderValueToIndexInArray = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // inorder => left => root => right
        // postorder => left => right => root. The root of answer tree is the last elements of postorder

        // The build process will be:
        //  1. pick the last node of postorder -> it will be root of a subtree. In the beginning it's THE root
        //  2. Find this node in inorder traversal. It's in-order, so the root will split the inorder array into
        // left part and right part.
        //  3. construct current node from the root — from postorder value
        //  4. run the same algorithm for the left & and the right part of the tree
        // NOTE: to detect NULL value, we pass in the helper function the boundaries where the left & right tree could be
        // in in-order traversal.

        this.postorder = postorder;
        this.inorder = inorder;
        // start from the last postorder element
        postOrderNodeIndex = postorder.length - 1;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder) {
            inorderValueToIndexInArray.put(val, idx++);
        }
        return helper(0, inorder.length - 1);

    }

    public TreeNode helper(int leftIndexBound, int rightIndexBound) {
        // if there is no elements to construct subtrees
        if (leftIndexBound > rightIndexBound) {
            return null;
        }

        // pick up postOrderNodeIndex element as a root
        int rootPostorderVal = postorder[postOrderNodeIndex];
        TreeNode root = new TreeNode(rootPostorderVal);

        // root splits inorder list
        // into left and right subtrees
        int inorderIndexOfPostorderVal = inorderValueToIndexInArray.get(rootPostorderVal);

        // recursion
        postOrderNodeIndex--;
        // build right subtree
        root.right = helper(inorderIndexOfPostorderVal + 1, rightIndexBound);
        // build left subtree
        root.left = helper(leftIndexBound, inorderIndexOfPostorderVal - 1);
        return root;
    }
}
