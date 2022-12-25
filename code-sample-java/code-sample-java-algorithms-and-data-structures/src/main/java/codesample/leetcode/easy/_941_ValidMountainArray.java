package codesample.leetcode.easy;

/**
 * 941. Valid Mountain Array — https://leetcode.com/problems/valid-mountain-array/description/
 */
public class _941_ValidMountainArray {
    static class Solution {
        public boolean validMountainArray(int[] arr) {

            int n = arr.length;

            // too small or not growing in the start
            if (n <= 2 || arr[1] <= arr[0]) {
                return false;
            }


            int left = 1;

            // grow from left to right
            while (left != n && arr[left - 1] < arr[left]) {
                left++;
            }
            // only growing or flattered
            if (left == n || arr[left - 1] == arr[left]) {
                return false;
            }

            while (left != n && arr[left - 1] > arr[left]) {
                left++;
            }

            return left == n;
        }
    }
}
