package codesample.other.educative._02_two_pointers;

import java.util.Arrays;

public class _05_TripletSumCloseToTarget {
    public static int searchTriplet(int[] arr, int targetSum) {

        // we sort the array to make finding differents easier

        Arrays.sort(arr); // need to make fiding number easier n * log(n) with qsort
        // we fix one value

        // internal array would have: value, left, right, target
        // when sum value+left+right.
        // if exactly target => return result
        // if > than target sum: move right pointer. Calculate absolute difference.
        // if < than target sum: move left pointers. Calculate absolute difference.
        // if at any point absolute difference becomes larger -> there is no point to continue iteratig
        // (^ how true if that?)
        // at the end of one cycle return absolute difference. If it is zero -> early exist,
        // if not, try again with different fixed number

        int smallestDifference = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int diffWithTarget = targetSum - arr[i] - arr[left] - arr[right];
                if (diffWithTarget == 0) {
                    return diffWithTarget;
                }

                if (Math.abs(diffWithTarget) < Math.abs(smallestDifference)) {
                    smallestDifference = diffWithTarget;
                }

                if (diffWithTarget > 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return targetSum - smallestDifference;
    }

    public static void main(String[] args) {
        System.out.println(_05_TripletSumCloseToTarget.searchTriplet(new int[]{-3, -1, 1, 2}, 1)); // exp: 0
        System.out.println(_05_TripletSumCloseToTarget.searchTriplet(new int[]{1, 0, 1, 1}, 100)); // exp: 3
        System.out.println(_05_TripletSumCloseToTarget.searchTriplet(new int[]{0, 1, 2}, 0)); // exp: 0
    }
}
