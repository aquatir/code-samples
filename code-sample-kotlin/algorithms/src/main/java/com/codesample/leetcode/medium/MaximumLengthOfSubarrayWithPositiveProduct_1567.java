package com.codesample.leetcode.medium;

public class MaximumLengthOfSubarrayWithPositiveProduct_1567 {
    static class Solution {

        public int getMaxLen(int[] nums) {
            throw new RuntimeException("Not implemented yet");
        }

        // left to right, then right to left
        public int getMaxLenTwoPass(int[] nums) {

            // 0 -> not positive. 0 == RESET

            int n = nums.length;
            int maxLeft = 0;

            boolean inNegArray = false;
            int positives = 0;
            int negatives = 0;

            for (int i = 0; i < n; i++) {
                int num = nums[i];

                if (num == 0) {
                    positives = 0;
                    negatives = 0;
                    inNegArray = false;
                } else if ( num > 0) {
                    if (inNegArray) {
                        negatives++;
                    } else {
                        positives++;
                    }
                } else { // num < 0
                    if (inNegArray) {
                        inNegArray = false;
                        positives = positives + negatives + 1; // add current number too
                        negatives = 0;
                    } else {
                        inNegArray = true;
                        negatives++;
                    }
                }

                if (inNegArray) {
                    maxLeft = Math.max(
                            maxLeft,
                            negatives - 1
                    );
                } else {
                    maxLeft = Math.max(maxLeft, positives);
                }
            }

            int maxRight = 0;
            positives = 0;
            negatives = 0;
            inNegArray = false;

            for (int i = 0; i < n; i++) {
                int num = nums[n - 1 - i];

                if (num == 0) {
                    positives = 0;
                    negatives = 0;
                    inNegArray = false;
                } else if ( num > 0) {
                    if (inNegArray) {
                        negatives++;
                    } else {
                        positives++;
                    }
                } else { // num < 0
                    if (inNegArray) {
                        inNegArray = false;
                        positives = positives + negatives + 1; // add current number too
                        negatives = 0;
                    } else {
                        inNegArray = true;
                        negatives++;
                    }
                }

                if (inNegArray) {
                    maxRight = Math.max(
                            maxRight,
                            negatives - 1
                    );
                } else {
                    maxRight = Math.max(maxRight, positives);
                }
            }


            return Math.max(maxLeft, maxRight);
        }
    }

    public static void main(String[] args) {
        final var sol = new Solution();

        System.out.println(sol.getMaxLen(new int[] {5,-20,-20,-39,-5,0,0,0,36,-32,0,-7,-10,-7,21,20,-12,-34,26,2})); // expected = 8 starting from -10
        System.out.println(sol.getMaxLen(new int[] {1,2,3,5,-6,4,0,10})); // expected = 4
        System.out.println(sol.getMaxLen(new int[] {-16,0,-5,2,2,-13,11,8})); // expected 6
        System.out.println(sol.getMaxLen(new int[] {-1,-2,-3,0,1})); // expected = 2;

        System.out.println(sol.getMaxLenTwoPass(new int[] {5,-20,-20,-39,-5,0,0,0,36,-32,0,-7,-10,-7,21,20,-12,-34,26,2})); // expected = 8 starting from -10
        System.out.println(sol.getMaxLenTwoPass(new int[] {1,2,3,5,-6,4,0,10})); // expected = 4
        System.out.println(sol.getMaxLenTwoPass(new int[] {-16,0,-5,2,2,-13,11,8})); // expected 6
        System.out.println(sol.getMaxLenTwoPass(new int[] {-1,-2,-3,0,1})); // expected = 2;

        // at the end of -7,-10,-7,21,20,-12,-34,26,2
        // inNegArray = true
        //
    }
}
