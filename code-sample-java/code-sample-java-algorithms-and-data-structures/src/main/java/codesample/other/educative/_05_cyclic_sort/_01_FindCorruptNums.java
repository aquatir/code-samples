package codesample.other.educative._05_cyclic_sort;

/**
 * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’. The array originally contained
 * all the numbers from 1 to ‘n’, but due to a data error, one of the numbers got duplicated which also resulted in one
 * number going missing. Find both these numbers.
 *
 * Example 1:
 *
 * Input: [3, 1, 2, 5, 2]
 * Output: [2, 4]
 * Explanation: '2' is duplicated and '4' is missing.
 * Example 2:
 *
 * Input: [3, 1, 2, 3, 6, 4]
 * Output: [3, 5]
 * Explanation: '3' is duplicated and '5' is missing.
 */
class _01_FindCorruptNums {

    public static int[] findNumbers(int[] nums) {
        // How to find duplicate => when need to replace => element is already in place;
        // How to find missing => cycle sort. Missing id will have incorrect element;

        // we WILL find duplicate while cycling
        // we WILL find missing why checking the array after cycle


        int i = 0;
        while (i < nums.length) {
            int element = nums[i];
            int expectedElementIndex = element - 1;
            if (element != nums[expectedElementIndex]) {
                int tmp = nums[expectedElementIndex];
                nums[expectedElementIndex] = element;
                nums[i] = tmp;
            }
            else {
                i++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            int element = nums[j];
            if (element - 1 != j) {
                return new int[] {element, j + 1};
            }
        }


        return new int[] { -1, -1};
    }


}

