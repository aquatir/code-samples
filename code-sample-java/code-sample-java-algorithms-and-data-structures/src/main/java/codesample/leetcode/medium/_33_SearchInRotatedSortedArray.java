package codesample.leetcode.medium;

/**
 * 33. Search in Rotated Sorted Array — https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class _33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        // Seems like binary serach + extra checks, e.g. jump over the middle.
        // is has two parts with a split
        // !after the split every element on the right is 100% LESS than element of the left.

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;
            int elementAtMid = nums[mid];
            if (elementAtMid == target) {
                return mid;
            }

            if (nums[left] <= elementAtMid) {
                // left side is sorted in ascending order
                if (target >= nums[left] && target < elementAtMid) {
                    right = mid - 1;
                } else { //target > nums[mid]
                    left = mid + 1;
                }
            } else { // right side is sorted in ascending order
                if (target > elementAtMid && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
