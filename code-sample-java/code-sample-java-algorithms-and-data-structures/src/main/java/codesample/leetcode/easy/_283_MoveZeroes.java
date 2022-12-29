package codesample.leetcode.easy;

/**
 * 283. Move Zeroes — https://leetcode.com/problems/move-zeroes/description/
 */
public class _283_MoveZeroes {
    class Solution {
        public void moveZeroes(int[] nums) {

            // step 1: "remove" zeroes, counting
            // step 2: put them back in

            int n = nums.length;
            int reader = 0;
            int writer = 0;
            int zeroes = 0;


            while (reader != n) {
                if (nums[reader] != 0) {
                    nums[writer] = nums[reader];
                    writer++;
                } else {
                    zeroes++;
                }
                reader++;
            }

            // fill zeroes back
            int i = n - 1;
            while (zeroes > 0) {
                nums[i] = 0;
                zeroes--;
                i--;
            }
        }
    }
}
