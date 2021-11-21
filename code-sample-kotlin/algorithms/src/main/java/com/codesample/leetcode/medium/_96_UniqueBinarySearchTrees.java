package com.codesample.leetcode.medium;

/**
 * 96. Unique Binary Search Trees — https://leetcode.com/problems/unique-binary-search-trees/
 *
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 */
public class _96_UniqueBinarySearchTrees {
    static class Solution {

        // doesn't seems to be a math problem?

        // how many ways to create binary tree of 2:
        // What is we know how much we can get from k
        // what is k+1?

        // if it k * (k+1) ? as any new node can be a root of BTS and we know how many
        // BTSs are there for k.
        // or is it more?

        // R0 = 1
        // R1 = 1
        // R2 = 2
        // R3 = 5.  (R3 = 3 * )
        // R4 =

        // What is number of trees with root at low and max number = high?
        //
        // if a sum of all the tree:
        // a) starting in low and finishing in low, low+1, low+2 up until high WITHOUT high
        // MULTIPLIED
        // b) by number of trees starting at low, low+1, low+2 and finishing with high.
        // essentially, we get a recursive solution...?

        public int numTrees(int n) {
            return treesFrom(1, n, new int[21][21], "");
        }

        int treesFrom(int low, int high, int[][] memo, String spaces) {

            if (low >= high) return 1;

            int total = 0;

            System.out.println(spaces + "Sum " + low + " — " + high + " is:" );
            for (int i = low; i<= high; i++) {
//                // starts with low and finished with number 1 less then high.
//                if (memo[low][i-1] == 0) {
//                    memo[low][i-1] = treesFrom(low, i - 1, memo);
//                }
//
//                if (memo[i+1][high] == 0) {
//                    memo[i+1][high] = treesFrom(i + 1, high, memo);
//                }
//
//                total += memo[low][i-1] * memo[i+1][high];

                String spaces2 = spaces + "  ";
                System.out.println(spaces2 + "trees: " +  low + " — " + (i - 1) + " * trees: " + (i+1) + " — " + high);
                total += treesFrom(low, i - 1, memo, spaces2) * treesFrom(i + 1, high, memo, spaces2);
            }

            return total;
        }
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.numTrees(4));
    }
}
