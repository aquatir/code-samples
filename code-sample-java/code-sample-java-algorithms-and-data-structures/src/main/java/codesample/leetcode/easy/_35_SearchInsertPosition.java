package codesample.leetcode.easy;

/**
 * 35. Search Insert Position — https://leetcode.com/problems/search-insert-position/
 *
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class _35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        if (target > nums[right]) {
            return nums.length;
        }

        while (left <= right) {
            int mid = left + (right-left)/2;
            int numAtMid = nums[mid];

            if (numAtMid == target) {
                return mid;
            } else {
                if (target > numAtMid) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return left;
    }
}
