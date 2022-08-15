package codesample.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 47. Permutations II — https://leetcode.com/problems/permutations-ii/
 * <p>
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 */
public class _47_PermutationsII {

    // Approach 1: dedublicate at the end
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        Set<List<Integer>> resultAsSet = new HashSet<>();
//
//        Queue<List<Integer>> queue = new ArrayDeque<>();
//        for (int i = 0; i < nums.length; i++) {
//            var num = nums[i];
//            if (i == 0) {
//                List<Integer> list = new ArrayList<Integer>();
//                list.add(num);
//                queue.offer(list);
//            } else {
//                int curPermutations = queue.size();
//                for (int j = 0; j < curPermutations; j++) {
//                    List<Integer> perm = queue.poll();
//                    for (int k = 0; k <= perm.size(); k++) {
//                        List<Integer> newPerm = new ArrayList<>(perm);
//                        newPerm.add(k, num);
//                        queue.offer(newPerm);
//                    }
//                }
//            }
//        }
//
//        for (List<Integer> qEntry : queue) {
//            resultAsSet.add(qEntry);
//        }
//
//        return new ArrayList<>(resultAsSet);
//    }

    // Approach 2: dedublicate all the time
    public List<List<Integer>> permuteUnique(int[] nums) {

        Queue<List<Integer>> queue = new ArrayDeque<>();
        Set<List<Integer>> partialPermutations = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            var num = nums[i];
            if (i == 0) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(num);
                queue.offer(list);
                partialPermutations.add(list);
            } else {
                int curPermutations = queue.size();
                for (int j = 0; j < curPermutations; j++) {
                    List<Integer> perm = queue.poll();
                    for (int k = 0; k <= perm.size(); k++) {
                        List<Integer> newPerm = new ArrayList<>(perm);
                        newPerm.add(k, num);
                        if (partialPermutations.contains(newPerm)) {
                            continue;
                        } else {
                            partialPermutations.add(newPerm);
                            queue.offer(newPerm);
                        }
                    }
                }
            }
        }

        return partialPermutations.stream().filter(a -> a.size() == nums.length).collect(Collectors.toList());
    }
}
