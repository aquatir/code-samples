package codesample.leetcode.medium;

/**
 * 334. Increasing Triplet Subsequence — https://leetcode.com/problems/increasing-triplet-subsequence/description/
 */
public class _334_IncreasingTripletSubsequence {

    // // approach 1: brute-force (n^3)
    // public boolean increasingTriplet(int[] nums) {
    //     int n = nums.length;
    //     for (int i = 0; i < n; i++) {
    //         for (int j = i+1; j < n; j++) {
    //             for (int k = j+1; k < n; k++) {
    //                 if (nums[i] < nums[j] && nums[j] < nums[k]) {
    //                     return true;
    //                 }
    //             }
    //         }
    //     }
    //     return false;
    // }

    // approach 2: greedy tracking the min value
    // track 2 indexes: lowestIndex and midIndex
    // - lowestIndex is always equal to the index of the lowest number if array
    // - midIndex is the next after lowestIndex but not equal to it
    // this way the [lowestIndex, midIndex] always is a correct "growing" array
    //  when we encounter the third element (i != lowerIndex && i != midIndex)
    //   check if the condition (nums[i] < nums[midIndex] < nums[lowestIndex]) holds
    //      if yes, return true
    //      else continue
    // return false if you have never returned true
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int lowestIndex = -1;
        int midIndex = -1;

        for (int i = 0; i < n; i++) {
            if (lowestIndex == -1 || nums[i] < nums[lowestIndex]) {
                lowestIndex = i;
            }
            if (lowestIndex != i) {
                if (midIndex == -1) {
                    if (nums[i] > nums[lowestIndex]) {
                        midIndex = i;
                    }
                } else {
                    if (nums[i] > nums[lowestIndex] && nums[i] < nums[midIndex]) {
                        midIndex = i;
                    }
                }
            }

            if (midIndex != -1 && lowestIndex != -1 && midIndex != i && lowestIndex != i) {
                if (nums[i] > nums[lowestIndex] && nums[i] > nums[midIndex]) {
                    return true;
                }
            }

        }
        return false;
    }

    // approach 3: leetcode magical solution
    //  simply find first, then second, then third
//    public boolean increasingTriplet(int[] nums) {
//        int first_num = Integer.MAX_VALUE;
//        int second_num = Integer.MAX_VALUE;
//        for (int n: nums) {
//            if (n <= first_num) {
//                first_num = n;
//            } else if (n <= second_num) {
//                second_num = n;
//            } else {
//                return true;
//            }
//        }
//        return false;
//    }
}
