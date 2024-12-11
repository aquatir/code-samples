package codesample.leetcode.easy;

/** 303. Range Sum Query - Immutable — https://leetcode.com/problems/range-sum-query-immutable/description/ */
public class _303_RangeSumQueryImmutable {

    class NumArray {

        int[] nums;
        int[] leftSum;

        public NumArray(int[] nums) {
            this.leftSum = new int[nums.length];
            this.nums = nums;
            this.leftSum[0] = this.nums[0];
            for (int i = 1; i < this.nums.length; i++) {
                this.leftSum[i] = this.leftSum[i-1] + this.nums[i];
            }
        }

        public int sumRange(int left, int right) {
            var res = this.leftSum[right] - this.leftSum[left] + this.nums[left];
            return res;
        }

        class NumArrayOneArray {

            int[] leftSum;

            public NumArrayOneArray(int[] nums) {
                this.leftSum = new int[nums.length + 1];
                for (int i = 0; i < nums.length; i++) {
                    this.leftSum[i + 1] = this.leftSum[i] + nums[i];
                }
            }

            public int sumRange(int left, int right) {
                var res = this.leftSum[right + 1] - this.leftSum[left];
                return res;
            }
        }
    }
}
