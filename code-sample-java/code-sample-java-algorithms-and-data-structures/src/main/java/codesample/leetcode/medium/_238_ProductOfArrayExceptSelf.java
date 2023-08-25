package codesample.leetcode.medium;

/**
 * 238. Product of Array Except Self — https://leetcode.com/problems/product-of-array-except-self/description/
 */
public class _238_ProductOfArrayExceptSelf {

    // approach 1: precompute 2 arrays
    //  - mults of everything to the left of i
    //  - mults of everything to the right of i
    // then return a result for each i by multiplying left by right
    // linear, because each pre-compute and the output are 3 linear ops
    // NOTE: instead of this unclear edge case logic you can treat prodRightToLeft[i] as "prod of all elements to the right of i"
    //  and prodLeftToRight as "prod of all elements to the left of i"
    //  this makes computation much less complex beacuse you can simply mult prodRightToLeft[i] * prodLeftToRight[i] to get result[i]
//    public int[] productExceptSelf(int[] nums) {
//
//        var n = nums.length;
//
//        var prodRightToLeft = new int[n];
//        prodRightToLeft[n - 1] = nums[n - 1];
//        for (int i = n - 2; i >= 0; i--) {
//            prodRightToLeft[i] = nums[i] * prodRightToLeft[i + 1];
//        }
//
//        var prodLeftToRight = new int[n];
//        prodLeftToRight[0] = nums[0];
//        for (int i = 1; i < n; i++) {
//            prodLeftToRight[i] = nums[i] * prodLeftToRight[i - 1];
//        }
//
//        var result = new int[n];
//        result[0] = prodRightToLeft[1];
//        result[n - 1] = prodLeftToRight[n - 2];
//        for (int i = 1; i < n - 1; i++) {
//            result[i] = prodLeftToRight[i - 1] * prodRightToLeft[i + 1];
//        }
//
//        return result;
//    }
//

    // approach 2: precompute the mults only one way
    //  then iterate + compute for the other way as well
    // NOTE: instead of this unclear edge case logic you can treat prodRightToLeft[i] as "prod of all elements to the right of i"
//    public int[] productExceptSelf(int[] nums) {
//
//        var n = nums.length;
//
//        var prodRightToLeft = new int[n];
//        prodRightToLeft[n-1] = nums[n-1];
//        for (int i = n-2; i >= 0; i--) {
//            prodRightToLeft[i] = nums[i] * prodRightToLeft[i+1];
//        }
//
//        var prodLeftToRight = 1;
//
//        var result = new int[n];
//        result[0] = prodRightToLeft[1];
//
//        for (int i = 1; i < n - 1; i++) {
//            prodLeftToRight = prodLeftToRight * nums[i-1];
//            result[i] = prodLeftToRight * prodRightToLeft[i+1];
//        }
//        result[n-1] = prodLeftToRight * nums[n-2];
//
//        return result;
//    }

    // approach 3: without O(1) extra space, brute force with O(n^2) complexity
    //  Time Limit exceeded
//    public int[] productExceptSelf(int[] nums) {
//        var n = nums.length;
//        var res = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            int left = 1;
//            for (int j = 0; j < i; j++) {
//                left *= nums[j];
//            }
//
//            int right = 1;
//            for (int j = i+1; j < n; j++) {
//                right *= nums[j];
//            }
//
//            res[i] = left * right;
//        }
//
//        return res;
//    }

    // also O(1) space with complexity
    // approach 4: precompute the mults only one way and store them in result array
    // then re-calculate the values inside the result array
    public int[] productExceptSelf(int[] nums) {

        var n = nums.length;

        var result = new int[n];
        result[n-1] = 1;
        for (int i = n-2; i >= 0; i--) {
            result[i] = nums[i+1] * result[i+1];
        }
        // result[i] now hold the multiplication of all elements to the right of i;

        int leftToRightMult = 1;
        for (int i = 0; i < n; i++) {
            result[i] = result[i] * leftToRightMult;
            leftToRightMult *= nums[i];
        }

        return result;
    }
}
