package com.codesample.other;

/**
 * You are given an array arr of N integers. For each index i, you are required to determine the number of contiguous subarrays that fulfill the following conditions:
 * The value at index i must be the maximum element in the contiguous subarrays, and
 * These contiguous subarrays must either start from or end on index i.
 * Signature
 * int[] countSubarrays(int[] arr)
 * Input
 * Array arr is a non-empty list of unique integers that range between 1 to 1,000,000,000
 * Size N is between 1 and 1,000,000
 * Output
 * An array where each index i contains an integer denoting the maximum number of contiguous subarrays of arr[i]
 * Example:
 * arr = [3, 4, 1, 6, 2]
 * output = [1, 3, 1, 5, 1]"
 */
public class ContiguousSubarrays {

    public int[] contiguousSubarrays(int[] array) {
        int n = array.length;
        return new int[n];
    }

    public static void main(String[] args) {
        var s = new ContiguousSubarrays();
        System.out.println(s.contiguousSubarrays(new int[]{3, 4, 1, 6, 2})); // expected: [1, 3, 1, 5, 1]
    }
}
