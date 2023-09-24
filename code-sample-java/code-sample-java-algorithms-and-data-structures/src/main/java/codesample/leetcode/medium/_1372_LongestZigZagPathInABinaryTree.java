package codesample.leetcode.medium;

/**
 * 1372. Longest ZigZag Path in a Binary Tree — https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/description/
 */
public class _1372_LongestZigZagPathInABinaryTree {

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


    int max = 0;

    public int longestZigZag(TreeNode root) {

        // base cases
        if (root == null) {
            return max;
        }
        // stupid initial condition that only 1 node must be treated as zero
        if (root.left == null && root.right == null) {
            return 0;
        } else {
            max = 1;
        }

        // run DFS alternating between left and right. Keep nextLeft flag
        // to know what to do next
        longestZigZag(root.left, false, 1);
        longestZigZag(root.right, true, 1);

        return max;
    }

    private void longestZigZag(TreeNode node, boolean nextLeft, int curZigZagLength) {
        if (node == null) {
            return;
        }

        // if you can go left and [nextLeft] => got a zigzag
        // if not [nextLeft] => not a zigzag, so refresh curZigZagLength to zero
        if (node.left != null) {
            if (nextLeft) {
                max = Math.max(max, curZigZagLength + 1);
                longestZigZag(node.left, false, curZigZagLength + 1);
            } else {
                longestZigZag(node.left, false, 1);
            }
        }

        // same as above, but fo right. If can go right and not [nextLeft] (which means next must be right)
        // increment zigzag
        // if not => reset zigzag count
        if (node.right != null) {
            if (!nextLeft) {
                max = Math.max(max, curZigZagLength + 1);
                longestZigZag(node.right, true, curZigZagLength + 1);
            } else {
                longestZigZag(node.right, true, 1);
            }
        }
    }
}
