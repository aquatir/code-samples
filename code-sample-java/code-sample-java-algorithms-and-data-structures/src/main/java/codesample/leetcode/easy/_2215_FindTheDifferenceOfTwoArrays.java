package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 2215. Find the Difference of Two Arrays — https://leetcode.com/problems/find-the-difference-of-two-arrays/description
 */
public class _2215_FindTheDifferenceOfTwoArrays {

    // O(n) with [O(n) <= 2n]  extra space
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        var num1NotInNum2 = new HashSet<Integer>();
        var num2NotInNum1 = new HashSet<Integer>();

        // add all elements to each set,
        // then check and possibly remove elements that are also present in another list
        // the return result

        for (var n: nums1) {
            num1NotInNum2.add(n);
        }

        for (var n: nums2) {
            num2NotInNum1.add(n);
        }

        var elesToRemove1 = new HashSet<Integer>();
        for (var n: num1NotInNum2) {
            if (num2NotInNum1.contains(n)) {
                elesToRemove1.add(n);
            }
        }

        var elesToRemove2 = new HashSet<Integer>();
        for (var n: num2NotInNum1) {
            if (num1NotInNum2.contains(n)) {
                elesToRemove2.add(n);
            }
        }

        num1NotInNum2.removeAll(elesToRemove1);
        num2NotInNum1.removeAll(elesToRemove2);

        var res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<>(num1NotInNum2));
        res.add(new ArrayList<>(num2NotInNum1));

        return res;
    }
}
