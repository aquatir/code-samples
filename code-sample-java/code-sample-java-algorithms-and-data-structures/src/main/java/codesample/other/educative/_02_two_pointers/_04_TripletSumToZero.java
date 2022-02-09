package codesample.other.educative._02_two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _04_TripletSumToZero {

    public static List<List<Integer>> searchTriplets(int[] arr) {
        List<List<Integer>> triplets = new ArrayList<>();

        // input: numbers
        // output: triplets

        // Having two numbers we can try getting target from the map.
        // In order to do it traverse array once: save the targets
        // traverse again with two pointers
        // when the target - (sum of two pointers' values) adds up to zero -> write down all tree numbers

        // alternative:
        // sort array
        // fix one number
        // find a pair

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            findPair(arr, triplets, arr[i], i + 1);
        }

        return triplets;
    }

    public static void findPair(int[] arr, List<List<Integer>> triplets, int fixedValue, int searchFrom) {
        int left = searchFrom;
        int right = arr.length - 1;

        while (left < right) {
            if (fixedValue + arr[left] + arr[right] == 0) {
                triplets.add(Arrays.asList(fixedValue, arr[left], arr[right]));
                left++;
                right--;

                while (left < right && arr[left] == arr[left - 1]) {
                    left++;
                }

                while (left < right && arr[right] == arr[right + 1]) {
                    right--;
                }
            } else {
                if (fixedValue + arr[left] + arr[right] > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(_04_TripletSumToZero.searchTriplets(new int[]{-3, 0, 1, 2, -1, 1, -2})); //exp; [-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]
        System.out.println(_04_TripletSumToZero.searchTriplets(new int[]{-5, 2, -1, -2, 3})); //exp; [[-5, 2, 3], [-2, -1, 3]]
    }
}
