package com.codesample.leetcode.medium;

/**
 * 152. Maximum Product Subarray — https://leetcode.com/problems/maximum-product-subarray/
 * <p>
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 * It is guaranteed that the answer will fit in a 32-bit integer.
 * A subarray is a contiguous subsequence of the array.
 */
public class MaximumProductSubarray_152 {
    static class Solution {
        public int maxProduct(int[] nums) {

            int n = nums.length;

            int leftProduct = 0;
            int rightProduct = 0;
            int max = nums[0];

            for (int i = 0; i < n; i++) {
                int oppositeIndex = n - 1 - i;

                leftProduct = (leftProduct == 0 ? 1 : leftProduct) * nums[i];
                rightProduct = (rightProduct == 0 ? 1 : rightProduct) * nums[oppositeIndex];
                max = Math.max(max, Math.max(leftProduct, rightProduct));
            }

            return max;
        }

        public int maxProductTwoPasses(int[] nums) {

            int n = nums.length;

            if (n == 1) {
                return nums[0];
            }

            int product = 1;
            int max = 0;

            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    product = 1;
                    continue;
                }
                product = product * nums[i];
                max = Math.max(nums[i], Math.max(product, max));
            }


            product = 1;
            for (int i = n - 1; i > 0; i--) {
                if (nums[i] == 0) {
                    product = 1;
                    continue;
                }
                product = product * nums[i];
                max = Math.max(nums[i], Math.max(product, max));
            }

            return max;
        }

    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.maxProduct(new int[]{2, -5, -2, -4, 3}));                        // expected 24 -> -2, -4, 3
        System.out.println(sol.maxProduct(new int[]{-3, 0, 1, -2}));                            // expected 1
        System.out.println(sol.maxProduct(new int[]{-1, -2, -3, 0}));                           // expected 6 -> -2,-3
        System.out.println(sol.maxProduct(new int[]{3, -1, 4}));                                // expected 4      -> 4
        System.out.println(sol.maxProduct(new int[]{2, 3, -2, 4}));                             // expected 6    -> 2,3
        System.out.println(sol.maxProduct(new int[]{2, 3, -2, 4, -1}));                         // expected 48   -> whole array
        System.out.println(sol.maxProduct(new int[]{-2, 0, -1}));                               // expected 0    -> 0
        System.out.println(sol.maxProduct(new int[]{0, 2}));                                    // expected 2      -> 2

    }
}
