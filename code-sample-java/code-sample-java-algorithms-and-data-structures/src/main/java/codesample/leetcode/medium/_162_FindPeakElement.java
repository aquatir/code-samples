package codesample.leetcode.medium;

/**
 * 162. Find Peak Element — https://leetcode.com/problems/find-peak-element/
 * <p>
 * A peak element is an element that is strictly greater than its neighbors.
 * <p>
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks,
 * * return the index to any of the peaks.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater
 * * than a neighbor that is outside the array.
 * <p>
 * You must write an algorithm that runs in O(log n) time.
 */
public class _162_FindPeakElement {
    public int findPeakElement(int[] nums) {

        // find any peak
        // binary sort to locate "local peak"?

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
