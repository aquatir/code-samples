package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 46. Permutations — https://leetcode.com/problems/permutations/
 *
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 */
public class _46_Permutations {

    // Approach 1: the slowest brute-force possible.
    public List<List<Integer>> permute(int[] nums) {

        Set<Integer> hashSet = new HashSet<>();
        for (var v: nums) {
            hashSet.add(v);
        }
        return permutate(hashSet);
    }

    public List<List<Integer>> permutate(Set<Integer> nums) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums.size() == 1) {
            var onlyValue = new ArrayList<>(nums).get(0);
            var internalArray = new ArrayList<Integer>();
            internalArray.add(onlyValue);
            result.add(internalArray);
            return result;
        }

        for (var v: nums) {
            var setWithoutFixed = new HashSet<>(nums);
            setWithoutFixed.remove(v);

            List<List<Integer>> permutations = permutate(setWithoutFixed);
            for (List<Integer> perms: permutations) {
                var newList = new ArrayList<>(perms);
                newList.add(v);
                result.add(newList);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        var s= new _46_Permutations();

        var r = s.permute(new int[]{1,2,3});
        for (var v: r) {
            System.out.print(v + ", "); // expected [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        }
    }
}
