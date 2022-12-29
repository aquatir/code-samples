package codesample.leetcode.easy;

/**
 * 1299. Replace Elements with Greatest Element on Right Side — 1299. Replace Elements with Greatest Element on Right Side
 */
public class _1299_ReplaceElementsWithGreatestElementOnRightSide {
    static class Solution {
        public int[] replaceElements(int[] arr) {
            int n = arr.length;
            var curMax = arr[n - 1];
            arr[n - 1] = -1;

            if (n == 1) {
                return arr;
            }

            for (int i = n - 2; i >= 0; i--) {
                var element = arr[i];
                arr[i] = curMax;
                curMax = Math.max(curMax, element);
            }

            return arr;
        }
    }
}
