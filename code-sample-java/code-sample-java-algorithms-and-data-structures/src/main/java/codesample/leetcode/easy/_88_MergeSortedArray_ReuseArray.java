package codesample.leetcode.easy;

public class _88_MergeSortedArray_ReuseArray {
    static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int insertPoint = nums1.length - 1;

            int left = m - 1;
            int right = n - 1;

            // nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
            // expected = [1,2,2,3,5,6]

            /**
             left = 2
             right = 2
             3 > 6 no => {
             result = [1,2,3,0,0,6]
             }
             left = 2
             right = 1
             3 > 6 no => {
             result = [1,2,3,0,5,6]
             }

             */
            while (insertPoint >= 0) {
                if (left != -1 && right != -1) { // both array are not empty -> pick the largest
                    if (nums1[left] >= nums2[right]) {
                        nums1[insertPoint] = nums1[left];
                        left--;
                    } else {
                        nums1[insertPoint] = nums2[right];
                        right--;
                    }
                } else if (left != -1 && right == -1) { // only left is not empty
                    nums1[insertPoint] = nums1[left];
                    left--;
                } else if (left == -1 && right != -1) { // only right is not empty
                    nums1[insertPoint] = nums2[right];
                    right--;
                } else { // both arrays are empty
                    return;
                }

                insertPoint--;
            }
        }
    }

    public static void main(String[] args) {
        var s = new Solution();

        var resultArr1 = new int[]{1,2,3,0,0,0};
        s.merge(resultArr1, 3, new int[]{2,5,6}, 3);
        for (int v: resultArr1) { // expected [1,2,2,3,5,6]
            System.out.print(v + ", ");
        }

        System.out.println();
        var resultArr2 = new int[]{0};
        s.merge(resultArr2, 0, new int[]{1}, 1);
        for (int v: resultArr2) { // expected [1]
            System.out.print(v + ", ");
        }
    }
}
