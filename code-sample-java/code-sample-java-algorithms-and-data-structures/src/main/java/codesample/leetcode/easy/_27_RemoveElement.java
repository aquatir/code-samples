package codesample.leetcode.easy;

public class _27_RemoveElement {
    static class Solution {
        public int removeElement(int[] nums, int val) {
            int reader = 0;
            int writer = 0;

            int n = nums.length;
            while (reader < n) {
                if (nums[reader] != val) {
                    nums[writer] = nums[reader];
                    writer++;
                }
                reader++;
            }
            return writer;
        }
    }


    public static void main(String[] args) {
        var s = new Solution();

        var nums = new int[]{3, 2, 2, 3};
        var numOfOutput = s.removeElement(nums, 2);

        for (int i = 0; i < numOfOutput; i++) {
            System.out.print(nums[i] + ", ");
        }
    }
}
