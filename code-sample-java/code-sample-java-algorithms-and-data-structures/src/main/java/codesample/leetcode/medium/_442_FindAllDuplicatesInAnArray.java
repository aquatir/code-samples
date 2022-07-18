package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 442. Find All Duplicates in an Array — https://leetcode.com/problems/find-all-duplicates-in-an-array/
 *
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice, return an array of all the integers that appears twice.
 *
 * You must write an algorithm that runs in O(n) time and uses only constant extra space.
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 * Example 2:
 *
 * Input: nums = [1,1,2]
 * Output: [1]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: []
 */
public class _442_FindAllDuplicatesInAnArray {
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> result = new HashSet<>(); // should use set instead => faster

        // constant space
        // O(n)
        // if 1 <= n <= 10^5 we can use radix sort to sort the array first in constant time
        // and them it's super simple. What else?
        //  can we flip the number (from + to -) and then somehow check that the number was flipped?
        //
        if (nums.length == 1) {
            return new ArrayList<>();
        }

        // approach 1: find loop, remove element
        // approach 2: brute force
        // approach 3: cycle sort...?
        // assume we find element which is already in place, what to do?
        //  write it down and skip current
        //  we swap element at max 1 time, so it's O(n). Not constant time, because have to save a result somewhere

        int i = 0;
        while (i != nums.length) {
            int element = nums[i];
            int expectedElementIndex = element - 1;

            if (i != expectedElementIndex) {
                if (nums[expectedElementIndex] == element) {
                    // current not in place, but the same exist => skip current element abd add to result
                    i++;
                    result.add(element);
                } else {
                    int tmp = nums[expectedElementIndex];
                    nums[expectedElementIndex] = element;
                    nums[i] = tmp;
                }
            } else {
                i++;
            }
        }

        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        var s = new _442_FindAllDuplicatesInAnArray();
        System.out.println(s.findDuplicates(new int[]{4,3,2,7,8,2,3,1})); // expected: [2,3]
        System.out.println(s.findDuplicates(new int[]{1, 1, 2})); // expected: [1]
        System.out.println(s.findDuplicates(new int[]{1})); // expected: [1]
    }
}
