package codesample.leetcode.easy;

/**
 * 905. Sort Array By Parity — https://leetcode.com/problems/sort-array-by-parity/description/
 */
public class _905_SortArrayByParity {

    class Solution {

        // Quick-sort style solution
        public int[] sortArrayByParity(int[] nums) {
            // even == 0, 2, 4
            // odd  == 1, 3, 5
            // even must be at beginning

            // left => stops on first odd in left half
            // right => stops on first even in right half
            //  than swap
            //  than shrink both left and right index
            //  than continue
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {

                // find first odd on left side
                while (left != right && nums[left] % 2 == 0) {
                    left++;
                }
                // find first even on right side
                while (right != left && nums[right] % 2 == 1) {
                    right--;
                }

                // swap
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;

                // shrink both pointers
                left++;
                right--;
            }

            return nums;
        }
    }
}
