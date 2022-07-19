package codesample.leetcode.easy;

/**
 * 1539. Kth Missing Positive Number — https://leetcode.com/problems/kth-missing-positive-number/
 *
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Return the kth positive integer that is missing from this array.
 *
 *
 *
 * Example 1:
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 *
 * Example 2:
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 */
public class _1539_KthMissingPositiveNumber {
    public int findKthPositive(int[] arr, int k) {

        int currentMissingElement = 1;
        int numberOfMissingFound = 0;

        // [2,3,4,7,11], k = 5

        // missing is either somewhere in the array or outside array.

        // 1: try to find it inside array
        // 2: if failed, keep incrementing the missing number

        int i = 0;
        while (i < arr.length) {
            // is the current missing in the correct position?
            if (arr[i] == currentMissingElement) {
                currentMissingElement++; // found the missing
                i++;
            } else {
                numberOfMissingFound++;
                if (numberOfMissingFound == k) {
                    return currentMissingElement;
                }
                currentMissingElement++;
                // didn't find the expected element
            }
        }

        currentMissingElement = arr[arr.length - 1];
        while (numberOfMissingFound != k) {
            numberOfMissingFound++;
            currentMissingElement++;
        }

        return currentMissingElement;
    }
}
