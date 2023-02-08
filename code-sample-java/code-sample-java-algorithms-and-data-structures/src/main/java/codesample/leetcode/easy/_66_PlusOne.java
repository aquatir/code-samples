package codesample.leetcode.easy;

/**
 * 66. Plus One — https://leetcode.com/problems/plus-one/description/
 */
public class _66_PlusOne {

    class Solution {
        public int[] plusOne(int[] digits) {
            int n = digits.length;

            int curIndex = n - 1;
            int reminder = 1;

            do {
                if (digits[curIndex] + reminder >= 10) {
                    reminder = 1;
                    digits[curIndex] = (digits[curIndex] + reminder) % 10;
                } else {
                    digits[curIndex] = digits[curIndex] + 1;
                    reminder = 0;
                }
                curIndex--;
            } while (reminder != 0 && curIndex > -1);

            if (reminder == 1) {
                var result = new int[n + 1];
                result[0] = 1;
                for (int i = 1; i < n; i++) {
                    result[i] = digits[i];
                }
                return result;
            } else {
                return digits;
            }
        }
    }
}
