package codesample.leetcode.medium;

/**
 * 287. Find the Duplicate Number — https://leetcode.com/problems/find-the-duplicate-number/
 *
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 */
public class _287_FindTheDuplicateNumber {

//    // with change of array
//    public int findDuplicate(int[] nums) {
//
//        // start cyclic sort. If you want to swap, but the element is in place already => it's a duplicate
//        // cyclic sort means that nums[i] must be in i-1 position
//        // e.g. "3" must be in position 2, because there are elements 1..n
//        for (int i = 0; i < nums.length; i++) {
//            int element = nums[i];
//            int expectedElementPosition = element - 1;
//
//            if (i != expectedElementPosition) {
//                if (nums[expectedElementPosition] == element) {
//                    return element;
//                } else {
//                    // swap
//                    int tmp = nums[expectedElementPosition];
//                    nums[expectedElementPosition] = element;
//                    nums[i] = tmp;
//                }
//            }
//        }
//
//        return -1;
//    }

    // without changes to array + constant memory
    public int findDuplicate(int[] nums) {
        // expected_sum_without_duplicated = (n * (n+1) / 2)
        //      n = 3 => [1,2,3] => sum = 6
        //      (3 * 4 / 2) = 6;
        //
        //      how to find if there is a duplicate? Count this and compare
        //
        //
        // no changes => no sort or cyclic sort or swaps even
        // constant memory usage
        // [1, 2, 2, 3, 4]

        // 1. brute force n^2: fix the element, compare it to all other elements.
        // 2. can we use the position of element at all? => doesn't seems so
        // 3. some binary magic? if element was met before XOR on that element will return a value which we know of
        // already
        // [1, 2, 3, 3] =>
        // 01 xor 10
        // xor 11 xor 11 => 3
        // [1, 2, 3, 3, 3] =>
        // 01 xor 10 xor 11 xor 11 xor 11 => 0.
        // [1, 2, 4 , 4, 4] =>
        // 001 xor 010 xor 100 xor 100 xor 100 => 111 ???
        // 4. Actually 2 might work with fast + slow pointer.
        //      if there is a duplicate than by jumping from element to the index of (element - 1) we will loop both arrays
        //      when they hit the same element => this is a duplicate

        // brute force
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (i == j) {
//                    continue;
//                } else if (nums[i] == nums[j]) {
//                    return nums[i];
//                }
//            }
//        }

        // [1, 2, 3, 4, 4]

        // 0 -> 1
        // 1 -> 2
        // 2 -> 3
        // 3 -> 4
        // 4 -> 4 loop

        // fast and slow pointer aka Floyd's cycle detection.

        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;

    }

    public static void main(String[] args) {
        var s = new _287_FindTheDuplicateNumber();
        System.out.println(s.findDuplicate(new int[]{1,3,4,2,2})); // expected 2
        System.out.println(s.findDuplicate(new int[]{3,1,3,4,2})); // expected 3
    }
}
