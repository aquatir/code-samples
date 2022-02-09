package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum — https://leetcode.com/problems/3sum/
 *
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 */
public class _15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            this.findPair(nums, triplets, nums[i], i + 1);
        }

        return triplets;
    }

    private void findPair(int[] arr, List<List<Integer>> triplets, int fixedValue, int searchFrom) {
        int left = searchFrom;
        int right = arr.length - 1;

        while (left < right) {
            if (fixedValue + arr[left] + arr[right] == 0) {
                triplets.add(Arrays.asList(fixedValue, arr[left], arr[right]));
                left++;
                right--;

                // skip repeating
                while (left < right && arr[left] == arr[left - 1]) {
                    left++;
                }

                // skip repeating
                while (left < right && arr[right] == arr[right + 1]) {
                    right--;
                }
            } else {
                if (fixedValue + arr[left] + arr[right] > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
    }
}
