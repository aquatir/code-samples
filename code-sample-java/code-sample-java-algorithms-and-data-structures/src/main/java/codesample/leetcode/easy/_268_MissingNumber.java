package codesample.leetcode.easy;

import java.util.HashSet;
import java.util.List;

/**
 * 268. Missing Number — https://leetcode.com/problems/missing-number/
 *
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is
 * missing from the array.
 *
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the
 * range since it does not appear in nums.
 *
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the
 * range since it does not appear in nums.
 */
public class _268_MissingNumber {
    // approach 1: sum of linear progression is a known maths fact
//    public int missingNumber(int[] nums) {
//
//        int actualSum = 0;
//        for (int i: nums) {
//            actualSum += i;
//        }
//
//        int max = nums.length;
//        int expectedSum = max * (max + 1) / 2;
//
//        return expectedSum - actualSum;
//    }

    // approach 2: xor
    public int missingNumber(int[] nums) {

        int xorExpected = 0;
        for (int i = 1; i <= nums.length; i++) {
            xorExpected = xorExpected ^ i;
        }

        int xorActual = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xorActual = xorActual ^ nums[i];
        }

        return xorExpected ^ xorActual;
    }

    public int missingNumberHashing(int[] nums) {
        var set = new HashSet<Integer>();

        var min = Integer.MAX_VALUE;
        var max = Integer.MIN_VALUE;
        for (var num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            set.add(num);
        }

        for (int num = min; num <= max; num++) {
            if (!set.contains(num)) {
                return num;
            }
        }


        if (min != 0) {
            return 0;
        } else {
            return max + 1;
        }
    }

}
