package codesample.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 167. Two Sum II - Input Array Is Sorted — https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 *
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 * Your solution must use only constant extra space.
 */
public class _167_Two_Sum_II_InputArrayIsSorted {

    // normal two sum solution — doesn't use the fact that the array is sorter + non-constant extra space as task suggests
//    public int[] twoSum(int[] numbers, int target) {
//
//        int n = numbers.length;
//
//        // value -> to Index
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            var left = numbers[i];
//
//            if (map.containsKey(target - left)) {
//                return new int[] {i + 1, map.get(target - left) + 1};
//            } else {
//                map.put(left, i);
//            }
//        }
//
//
//        return new int[] {-1, -1};
//    }

    // uses binary search to find the other number
//    public int[] twoSum(int[] numbers, int target) {
//
//        int n = numbers.length;
//
//        for (int i = 0; i < n; i++) {
//            var searchFor = target - numbers[i];
//
//            var bsIndex = binarySearch(numbers, i + 1, n, searchFor);
//            if (bsIndex != -1) {
//                return new int[] {i + 1, bsIndex + 1};
//            }
//        }
//
//        return new int[] {-1, -1};
//    }
//
//    public int binarySearch(int[] numbers, int from, int to, int target) {
//        var left = from;
//        var right = to;
//
//        while (left <= right && left != numbers.length) {
//            var mid = left + ((right - left) / 2);
//            if (numbers[mid] == target) {
//                return mid;
//            } else if (target > numbers[mid]) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return -1;
//    }

    // two indexes even easier solution
    public int[] twoSum(int[] numbers, int target) {
        var left = 0;
        var right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[] {left + 1, right + 1};
            } else if (sum > target) { // move right
                right--;
            } else {
                left++;
            }
        }

        return new int[] {-1, -1}; // no result
    }

    public static void main(String[] args) {
        var s = new _167_Two_Sum_II_InputArrayIsSorted();

        Arrays.stream(s.twoSum(new int[] {2,7,11,15}, 9)).forEach(it -> System.out.print(it + " -> ")); // expected [1,2]
    }
}
