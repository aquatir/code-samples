package codesample.leetcode.easy;

/**
 * 704. Binary Search — https://leetcode.com/problems/binary-search/
 *
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class _704_BinarySearch {
    public int search(int[] nums, int target) {
        // hot binary search works...
        // find index in the middle => either go to the left or right
        int left = 0;
        int right = nums.length;

        while (right >= left && left != nums.length) {
            int mid = left + (right-left)/2;
            int numAtMid = nums[mid];
            if (numAtMid == target) {
                return mid;
            } else {
                if (target > numAtMid) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }
}
