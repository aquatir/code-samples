package codesample.leetcode.easy;

/**
 * 26. Remove Duplicates from Sorted Array — https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 */
public class _26_RemoveDuplicatesfromSortedArray_InPlace {
    class Solution {
        public int removeDuplicates(int[] nums) {
            // -100 <= nums[i] <= 100 means that we can do Radix sort and reconstruct array later
            // -100 => index 0
            // 0 => index 100 ?
            // 100 => index 201

            if (nums.length == 1) {
                return 1;
            }

            int writer = 1;
            for (int reader = 1; reader < nums.length; reader++) {
                if (nums[reader] != nums[reader - 1]) { // different number encountered
                    nums[writer] = nums[reader];
                    writer++;
                }
            }
            return writer;
        }
    }
}
