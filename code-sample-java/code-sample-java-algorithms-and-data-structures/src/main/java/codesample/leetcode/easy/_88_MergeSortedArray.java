package codesample.leetcode.easy;

import java.util.Arrays;

public class _88_MergeSortedArray {
    static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int size = n + m;
            int[] result = new int[size];

            int left = 0;
            int right = 0;

            for (int i = 0; i < size; i++) {

                // if out of limit for left: save right
                if (left == m) {
                    result[i] = nums2[right];
                    right++;

                    // if out of limit for right: save left
                } else if (right == n) {
                    result[i] = nums1[left];
                    left++;

                    // if both are not out of limits: save the smaller and advance the index of saved.
                } else if (nums1[left] <= nums2[right]) {
                    result[i] = nums1[left];
                    left++;
                } else {
                    result[i] = nums2[right];
                    right++;
                }
            }

            for (int i = 0; i < size; i++) {
                nums1[i] = result[i];
            }
        }

        public static void main(String[] args) {
            var s = new Solution();
            int[] resultArr1 = new int[]{1, 2, 3, 0, 0, 0};
            s.merge(resultArr1, 3, new int[]{2, 5, 6}, 3);
            System.out.println(Arrays.toString(resultArr1));
        }
    }
}
