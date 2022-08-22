package codesample.leetcode.medium;

/**
 * 81. Search in Rotated Sorted Array II — https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * <p>
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 * <p>
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 * <p>
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
 * <p>
 * You must decrease the overall operation steps as much as possible.
 */
public class _81_SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            while (left < right && nums[left] == nums[left + 1]) {
                left++;
            }
            while (left < right && nums[right] == nums[right - 1]) {
                right--;
            }

            int mid = left + (right - left) / 2;
            int elementAtMid = nums[mid];
            if (elementAtMid == target) {
                return true;
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
        return false;
    }
}
