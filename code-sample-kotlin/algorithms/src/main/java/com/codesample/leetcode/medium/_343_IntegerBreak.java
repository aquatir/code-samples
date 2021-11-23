package com.codesample.leetcode.medium;

/**
 * 343. Integer Break — https://leetcode.com/problems/integer-break/
 *
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 * Return the maximum product you can get.
 */
public class _343_IntegerBreak {
    static class Solution {

        // 10 = 3 + 3 + 4 / 3х3х4 = 36
        // 15 = 10+5 / 10 * 5 (50)
        //   OR 5 + 5 + 5 / 5 * 5 * 5 (125)
        //   OR 2 + 2 + 2 + 2 + 2 + 2 + 1 / 2^6 * 1 = 32
        //   OR 3 + 3 + 3 + 3 + 3 / 3^5 = 9 * 9 * 3 = 81 * 3 = 243. More than 5.
        // Breaking by 2 => too little
        // Breaking by 5 => better then 2, but worse then 3.
        // breaking by 4 doesnt make sense as it's essentially the same as breaking by 2.
        // breaking by any power of anything doesn't make sence -> only break on prime numbers.
        // If breaking by 5 is worse then break by 3... seems like breaking by 3 always wins??
        public int integerBreak(int n) {
            if (n == 2) {
                return 1;
            }
            if (n == 3) {
                return 2;
            }

            int product = 1;
            while (n > 4) {
                product *= 3;
                n -= 3;
            }
            return product * n;
        }
    }
}
