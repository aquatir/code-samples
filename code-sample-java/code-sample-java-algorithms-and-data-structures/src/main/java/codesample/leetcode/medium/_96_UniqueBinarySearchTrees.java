package codesample.leetcode.medium;

/**
 * 96. Unique Binary Search Trees — https://leetcode.com/problems/unique-binary-search-trees/
 *
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 */
public class _96_UniqueBinarySearchTrees {
    static class Solution {

        public int numTrees(int n) {
            return allTrees(1, n, new int[21][21]);
        }

        public int allTrees(int start, int end, int[][] memo) {
            if (start >= end) {
                return 1;
            }

            int sum = 0;
            for (int i = start; i <= end; i++) {
                // assume i is the root;
                // > all subtrees on the left will be between [start, i-1]
                // > all subtrees on the right will be between [i+1, end]
                // left part of the tree => everything from start to current-1
                if (memo[start][i-1] == 0) {
                    memo[start][i-1] = allTrees(start, i - 1, memo);
                }

                // right part of the tree => everything from one over current to end
                if (memo[i+1][end] == 0) {
                    memo[i+1][end] = allTrees(i + 1, end, memo);
                }

                // total number of trees is the multiplication of two
                sum += memo[start][i-1] * memo[i+1][end];
            }

            return sum;
        }
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.numTrees(3)); // expected 5
        System.out.println(s.numTrees(4)); // expected 14
    }
}
