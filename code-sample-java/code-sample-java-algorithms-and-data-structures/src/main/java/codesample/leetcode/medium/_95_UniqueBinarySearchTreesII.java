package codesample.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * 95. Unique Binary Search Trees II — https://leetcode.com/problems/unique-binary-search-trees-ii/description/
 */
public class _95_UniqueBinarySearchTreesII {

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
        public List<TreeNode> generateTrees(int n) {
            // pick each elements as root -> recurse from there on either left tree of right tree. Build new trees from what you've got

            if (n == 0) {
                return new LinkedList<TreeNode>();
            }
            return generateTrees(1, n);
        }

        public LinkedList<TreeNode> generateTrees(int start, int end) {
            LinkedList<TreeNode> allTrees = new LinkedList<>();
            if (start > end) {
                allTrees.add(null);
                return allTrees;
            }

            // pick up a root
            for (int i = start; i <= end; i++) {
                // all possible left subtrees if i is choosen to be a root
                var leftTrees = generateTrees(start, i - 1);

                // all possible right subtrees if i is choosen to be a root
                var rightTrees = generateTrees(i + 1, end);

                // connect left and right trees to the root i
                for (TreeNode l : leftTrees) {
                    for (TreeNode r : rightTrees) {
                        TreeNode currentTree = new TreeNode(i);
                        currentTree.left = l;
                        currentTree.right = r;
                        allTrees.add(currentTree);
                    }
                }
            }

            return allTrees;
        }
    }
}
