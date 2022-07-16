package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. Find All Numbers Disappeared in an Array — https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array
 *
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 */
public class _448_FindAllNumbersDisappearedInAnArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int index = 0;
        while (index < nums.length) {
            int j = nums[index] - 1;
            if (nums[index] != nums[j]) {
                // swap
                int tmp = nums[index];
                nums[index] = nums[j];
                nums[j] = tmp;
            } else {
                index++;
            }
        }

        List<Integer> missingNumbers = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i + 1)) {
                missingNumbers.add(i + 1);
            }
        }

        return missingNumbers;
    }
}
