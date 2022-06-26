package codesample.leetcode.easy;

/**
 * 26. Remove Duplicates from Sorted Array — https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once. The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be
 * placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */
public class _26_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        // -100 <= nums[i] <= 100 means that we can do Radix sort and reconstruct array later

        // -100 => index 0
        // 0 => index 100 ?
        // 100 => index 201

        int[] radix = new int[201];
        for (int i: nums) {
            radix[i + 100]++;
        }

        int i = 0;
        for (int j = 0; j < radix.length; j++) {
            if (radix[j] > 0) {
                nums[i] = j - 100;
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        var sol = new _26_RemoveDuplicatesFromSortedArray();
        var nums = new int[] { 1, 1, 2 };

        var result = sol.removeDuplicates(nums);
        System.out.println(result);
        for (int i: nums) {
            System.out.print(i + ", ");
        }
    }
}
