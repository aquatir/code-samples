package codesample.leetcode.medium;

/**
 * 236. Lowest Common Ancestor of a Binary Tree — https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 */
public class _236_LowestCommonAncestorOfABinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.recurseTree(root, p, q);
        return this.ans;
    }

    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {

        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }

        // booth found on left or right side
        int bothFoundLeft = this.recurseTree(currentNode.left, p, q) ? 1 : 0;
        int bothFoundRight = this.recurseTree(currentNode.right, p, q) ? 1 : 0;
        // current node IS either P or Q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;

        // 3 cases:
        // either current node is answer 1 and answer 2 is on left subtree
        // or current node is answer 2 and answer 2 is in right subtree
        // or current not an answer, but current node is one of the ancestors to both
        //  all 3 can't be true at the same time
        if (mid + bothFoundLeft + bothFoundRight == 2) {
            this.ans = currentNode;
        }

        // for recursion: if any match found the recursive call is true
        return (mid + bothFoundLeft + bothFoundRight > 0);
    }
}
