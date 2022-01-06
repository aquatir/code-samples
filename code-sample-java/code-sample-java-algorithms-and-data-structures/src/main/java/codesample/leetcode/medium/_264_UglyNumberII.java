package codesample.leetcode.medium;

/**
 * 264. Ugly Number II — https://leetcode.com/problems/ugly-number-ii/
 *
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * Given an integer n, return the nth ugly number.
 */
public class _264_UglyNumberII {

    static class Solution {
        public int nthUglyNumber(int n) {
            // 2, 3, 5

            // ugly:
            // 2: 2, 4, 6, 8, 10
            // 3: 3,    6, 9, 12, 15
            // 5: 5,          10, 15, 20
            // some of those repeat.

            // find 2:
            // 2 == 1 -> false
            // numbersLeftToFind = 1
            // cur = 1;
            // numbersLeftToFind > 0 -> true =>
            //  cur = 2
            // cur > cur2 => true;

            if (n == 1) {
                return 1;
            }

            int[] ugly = new int[n];
            ugly[0] = 1;
            int index2 = 0, index3 = 0, index5 = 0;
            int factor2 = 2, factor3 = 3, factor5 = 5;
            for (int i = 1; i < n; i++) {
                int min = Math.min(Math.min(factor2, factor3), factor5);
                ugly[i] = min;

                if (factor2 == min) {
                    factor2 = 2*ugly[++index2];
                }

                if (factor3 == min) {
                    factor3 = 3*ugly[++index3];
                }

                if (factor5 == min) {
                    factor5 = 5*ugly[++index5];
                }
            }


            return ugly[n - 1];
        }
    }

    public static void main(String[] args) {
        var s = new Solution();

        System.out.println(s.nthUglyNumber(12)); // expected = 5 // 1, 2, 3, 4, 5
        System.out.println(s.nthUglyNumber(1690)); // expected = 2123366400
    }
}
