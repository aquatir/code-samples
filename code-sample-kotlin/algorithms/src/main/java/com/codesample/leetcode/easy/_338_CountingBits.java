package com.codesample.leetcode.easy;

/**
 * 338. Counting Bits — https://leetcode.com/problems/counting-bits/
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the
 * number of 1's in the binary representation of i.
 */
public class _338_CountingBits {
    static class Solution {

        // 0    0      0
        // 1    1      1
        // 2    10     1
        // 3    11     2
        // 4    100    1
        // 5    101    2
        // 6    110    2
        // 7    111    3
        // 8    1000   1
        // 9    1001   2
        // 10   1010   2
        // 11   1011   3
        // 12   1100   2
        // 13   1101   3
        // 14   1110   3
        // 15   1111   4
        // 16   10000  1
        // 17   10001  2

        public int[] countBits(int n) {
            int[] result = new int[n + 1];

            result[0] = 0;

            String kek = "";
            kek.length();

            for (int i = 1; i < n + 1; i++) {
                if ((i - 1) % 2 == 0) {
                    result[i] = result[i-1] + 1;
                } else {
                    result[i] = result[i/2];
                }
            }

            return result;
        }
    }
}
