package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 46. Permutations — https://leetcode.com/problems/permutations/
 * <p>
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 */
public class _46_Permutations {

    // Approach 1: the slowest brute-force possible.
//    public List<List<Integer>> permute(int[] nums) {
//
//        Set<Integer> hashSet = new HashSet<>();
//        for (var v: nums) {
//            hashSet.add(v);
//        }
//        return permutate(hashSet);
//    }
//
//    public List<List<Integer>> permutate(Set<Integer> nums) {
//
//        List<List<Integer>> result = new ArrayList<>();
//        if (nums.size() == 1) {
//            var onlyValue = new ArrayList<>(nums).get(0);
//            var internalArray = new ArrayList<Integer>();
//            internalArray.add(onlyValue);
//            result.add(internalArray);
//            return result;
//        }
//
//        for (var v: nums) {
//            var setWithoutFixed = new HashSet<>(nums);
//            setWithoutFixed.remove(v);
//
//            List<List<Integer>> permutations = permutate(setWithoutFixed);
//            for (List<Integer> perms: permutations) {
//                var newList = new ArrayList<>(perms);
//                newList.add(v);
//                result.add(newList);
//            }
//        }
//
//        return result;
//    }
    // Approach 2: on each step, go over all permutations and add new values to each possible place
    // then return only those which are of correct size
//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//
//        List<Integer> first = new ArrayList<>();
//        first.add(nums[0]);
//        result.add(first);
//
//        if (nums.length == 1) {
//            return result;
//        }
//
//        // for each number starting from the second
//        for (int i = 1; i < nums.length; i++) {
//
//            int size = result.size();
//            List<List<Integer>> newPerms = new ArrayList<>();
//
//            // for each permutation currently available in the result
//            // => extract it (== remove)
//            for (int j = 0; j < size; j++) {
//
//                // create a bunch more perms
//                List<Integer> current = result.get(j);
//                for (int k = 0; k <= current.size(); k++) {
//                    List<Integer> newPerm = new ArrayList(current);
//                    newPerm.add(k, nums[i]);
//                    newPerms.add(newPerm);
//                }
//            }
//            result.addAll(newPerms);
//        }
//        return result.stream().filter(a -> a.size() == nums.length).collect(Collectors.toList());
//    }

    // Approach 3: use queue to store temporary permutations. Only add result when result is of correct length
    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());

        for (int currentNumber : nums) {
            // we will take all existing permutations and add the current number to create new permutations
            int n = permutations.size();
            for (int i = 0; i < n; i++) {
                List<Integer> oldPermutation = permutations.poll();
                // create a new permutation by adding the current number at every position
                for (int j = 0; j <= oldPermutation.size(); j++) {
                    List<Integer> newPermutation = new ArrayList<>(oldPermutation);
                    newPermutation.add(j, currentNumber);
                    if (newPermutation.size() == nums.length) {
                        result.add(newPermutation);
                    } else {
                        permutations.add(newPermutation);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        var s = new _46_Permutations();

        var r = s.permute(new int[]{1, 2, 3});
        for (var v : r) {
            System.out.print(v + ", "); // expected [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        }
    }
}
