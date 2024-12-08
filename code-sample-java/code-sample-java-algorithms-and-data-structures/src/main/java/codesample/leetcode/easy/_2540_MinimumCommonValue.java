package codesample.leetcode.easy;

/**
 * 2540. Minimum Common Value — https://leetcode.com/problems/minimum-common-value/description/
 */
public class _2540_MinimumCommonValue {
    public int getCommon(int[] nums1, int[] nums2) {
        // approach 2 pointers from left. If match => return
        // if not match => increase the one which is lower

        var nums1Ptr = 0;
        var nums2Ptr = 0;

        while (nums1Ptr != nums1.length && nums2Ptr != nums2.length) {
            if (nums1[nums1Ptr] == nums2[nums2Ptr]) {
                return nums1[nums1Ptr];
            }

            if (nums1Ptr == nums1.length) {
                nums2Ptr++;
            } else if (nums2Ptr == nums2.length) {
                nums1Ptr++;
            } else if (nums1[nums1Ptr] > nums2[nums2Ptr]) {
                nums2Ptr++;
            } else {
                nums1Ptr++;
            }
        }

        return -1;
    }
}
