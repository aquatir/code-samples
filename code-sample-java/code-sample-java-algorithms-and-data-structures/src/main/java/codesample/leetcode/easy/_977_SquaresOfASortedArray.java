package codesample.leetcode.easy;

/**
 * 977. Squares of a Sorted Array — https://leetcode.com/problems/squares-of-a-sorted-array/
 * <p>
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 */
public class _977_SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        int[] squares = new int[nums.length];

        // Move from the SIDES of the array and add the lowest square -> much easier to
        // work with border conditions.

        int left = 0;
        int right = nums.length - 1;
        int nextInsertPoint = squares.length - 1;

        while (right >= left) {
            if (Math.abs(nums[right]) > Math.abs(nums[left])) {
                squares[nextInsertPoint] = nums[right] * nums[right];
                right--;
            } else {
                squares[nextInsertPoint] = nums[left] * nums[left];
                left++;
            }

            nextInsertPoint--;
        }

        return squares;
    }
}
