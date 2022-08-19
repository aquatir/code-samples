package codesample.leetcode.medium;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class _34_FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right-left)/2;

            int atMid = nums[mid];

            if (target > atMid) {
                left = mid + 1;
            } else if (target < atMid) {
                right = mid - 1;
            } else { // target == atMid;

                // equal => Calculate + return result;
                // calculation: move from mid until we find correct value
                left = mid;
                right = mid;

                while (left > 0 && nums[left-1] == target) {
                    left--;
                }
                while (right < (nums.length-1) && nums[right+1] == target) {
                    right++;
                }

                return new int[]{left, right};
            }
        }

        return new int[]{-1, -1};
    }
}
