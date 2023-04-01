package codesample.leetcode.medium;

/**
 * 189. Rotate Array — https://leetcode.com/problems/rotate-array/description/
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 */
public class _189_RotateArray {

    // rotate with extra memory
//    public void rotate(int[] nums, int k) {
//        int n = nums.length;
//        int[] result = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            int newIndex = newIndex(i, n, k);
//            result[newIndex] = nums[i];
//        }
//
//        for (int i = 0; i < n; i++) {
//            nums[i] = result[i];
//        }
//    }
//
//    private int newIndex(int curIndex, int length, int k) {
//        var res = curIndex + k;
//        return res % length;
//    }

    /**
     * rotate with special trick when you rotate the array by k, k elements from back comes to front.
     * <p>
     * Example n = 7, k = 3 <br>
     * Original List                   : 1 2 3 4 5 6 7 <br>
     * After reversing all numbers     : 7 6 5 4 3 2 1 <br>
     * After reversing first k numbers : 5 6 7 4 3 2 1 <br>
     * After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result
     */

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            var tmp = nums[startIndex];
            nums[startIndex] = nums[endIndex];
            nums[endIndex] = tmp;

            startIndex++;
            endIndex--;
        }
    }
}
