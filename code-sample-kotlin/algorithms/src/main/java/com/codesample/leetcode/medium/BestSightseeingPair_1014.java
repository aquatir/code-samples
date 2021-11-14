package com.codesample.leetcode.medium;

/**
 * 1014. Best Sightseeing Pair — https://leetcode.com/problems/best-sightseeing-pair/
 *
 * You are given an integer array values where values[i] represents the value of the ith sightseeing spot.
 * Two sightseeing spots i and j have a distance j - i between them.*
 * The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the
 * sightseeing spots, minus the distance between them. Return the maximum score of a pair of sightseeing spots.
 */
public class BestSightseeingPair_1014 {
    static class Solution {
        public int maxScoreSightseeingPair(int[] values) {

            int n = values.length;
            int maxSoFar = values[0];
            int result = 0;

            for (int i = 1; i < n; i++) {
                result = Math.max(result, maxSoFar + values[i] - i); // possible current max score
                maxSoFar = Math.max(maxSoFar, values[i] + i); // maybe updated new score
            }

            return result;
        }
    }

    public static void main(String[] args) {
        final var s = new Solution();

        System.out.println(s.maxScoreSightseeingPair(new int[]{2,7,7,2,1,7,10,4,3,3})); // exp: 16. -> 7, 10.
        System.out.println(s.maxScoreSightseeingPair(new int[]{8,1,5,2,6}));    // exp: 11 -> i = 0, j = 2
        System.out.println(s.maxScoreSightseeingPair(new int[]{1,2}));          // exp: 2
    }
}
