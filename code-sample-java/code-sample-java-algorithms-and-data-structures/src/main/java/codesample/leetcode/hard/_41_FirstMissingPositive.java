package codesample.leetcode.hard;

/**
 * 41. First Missing Positive — https://leetcode.com/problems/first-missing-positive/
 *
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 */
public class _41_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        // cycle sort ignoring less than 0 and over the array size
        int i = 0;

        while (i < nums.length) {
            int element = nums[i];
            int expectedElementIndex = element - 1;

            if (element <= 0 || element >= nums.length) {
                i++;
                continue;
            }

            if (element != nums[expectedElementIndex]) {
                int tmp = nums[expectedElementIndex];
                nums[expectedElementIndex] = element;
                nums[i] = tmp;
            } else {
                i++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            int element = nums[j];
            if (element != j + 1) { // element is missplaced
                return j + 1; // the correct element value
            }
        }
        return nums.length + 1; // if non are missplaced, return the first element which is not in the array
    }

    public static void main(String[] args) {
        var s = new _41_FirstMissingPositive();
        System.out.println(s.firstMissingPositive(new int[] {3})); // expected = 1
        System.out.println(s.firstMissingPositive(new int[] {1})); // expected = 2
        System.out.println(s.firstMissingPositive(new int[] {1, 2, 0})); // expected = 3
        System.out.println(s.firstMissingPositive(new int[] {-1, 1, 3, 4})); // expected = 2
        System.out.println(s.firstMissingPositive(new int[] {7, 8, 9, 11, 12})); // expected = 1

    }
}
