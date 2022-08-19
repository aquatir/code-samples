package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1200. Minimum Absolute Difference — https://leetcode.com/problems/minimum-absolute-difference/
 * <p>
 * Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
 * <p>
 * Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
 * <p>
 * a, b are from arr
 * a < b
 * b - a equals to the minimum absolute difference of any two elements in arr
 */
public class _1200_MinimumAbsoluteDifference {
    // Approach 1: brute force + sort. Time Limit Exceeded
//    public List<List<Integer>> minimumAbsDifference(int[] arr) {
//        List<List<Integer>> result = new ArrayList<>();
//        int curDiff = Integer.MAX_VALUE;
//
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = i; j < arr.length; j++) {
//                if (i == j) {
//                    continue;
//                }
//
//                int diff = Math.abs(arr[j] - arr[i]);
//                if (diff < curDiff) {
//                    curDiff = diff;
//                    result = new ArrayList<>();
//                    result.add(List.of(Math.min(arr[i], arr[j]), Math.max(arr[i], arr[j])));
//                } else if (diff > curDiff) {
//                    continue;
//                } else { // ==
//                    result.add(List.of(Math.min(arr[i], arr[j]), Math.max(arr[i], arr[j])));
//                }
//            }
//        }
//
//        Collections.sort(result, (o1, o2) -> Integer.compare(o1.get(0), o2.get(0)));
//        return result;
//    }

    // Approach 2: sort first (n*log(n)) than find diffs
    public List<List<Integer>> minimumAbsDifference(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int minDiff = nums[nums.length - 1];

        for (int i = 0; i < nums.length - 1; i++) {
            int curDiff = Math.abs(nums[i] - nums[i + 1]);
            if (curDiff == minDiff) {

                result.add(List.of(nums[i], nums[i + 1]));
            }
            if (curDiff < minDiff) {
                result = new ArrayList<>();
                result.add(List.of(nums[i], nums[i + 1]));
                minDiff = curDiff;
            }
        }
        return result;
    }
}
