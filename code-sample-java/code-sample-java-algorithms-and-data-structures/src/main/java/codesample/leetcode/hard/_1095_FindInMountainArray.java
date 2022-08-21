package codesample.leetcode.hard;

/**
 * 1095. Find in Mountain Array — https://leetcode.com/problems/find-in-mountain-array/submissions/
 * <p>
 * (This problem is an interactive problem.)
 * <p>
 * You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an
 * index does not exist, return -1.
 * <p>
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 * <p>
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that
 * attempt to circumvent the judge will result in disqualification.
 */
public class _1095_FindInMountainArray {

    interface MountainArray {
        int get(int index);
        int length();
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        // bin search for top until find the max (peak of mountaion array)
        // search in left part (left of peak) + search in right part (right of peak)
        // return result

        int left = 0;
        int right = mountainArr.length() - 1;

        // [1, 2, 1]
        // [1, 2, 3, 1]

        // [1,2,1] l=0,r=2
        // mid = 1
        // left = 1, r = 2
        // mid = 1

        // [1, 2, 3, 1] l=0,r=3
        // mid = 1
        // left = 1, r = 3
        // mid = 2
        // left = 2, r = 3
        // mid = 2
        // left = 3
        while (left < right) {
            int mid = left + (right - left) / 2;

            // element to the left is smaller than one to the right
            // we are on up slope
            // have to move left to mid+1
            // else move right
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int maxIndex = left;

        if (target == mountainArr.get(maxIndex)) {
            return maxIndex;
        } else {

            var resultLeft = binarySearch(0, maxIndex - 1, mountainArr, target, true);
            var resultRight = binarySearch(maxIndex + 1, mountainArr.length() - 1, mountainArr, target, false);

            if (resultLeft >= 0 && resultRight >= 0) {
                return Math.min(resultLeft, resultRight);
            } else if (resultLeft < 0) {
                return resultRight;
            } else {
                return resultLeft;
            }
        }
    }

    public int binarySearch(int left, int right, MountainArray mountainArr, int target, boolean isAscending) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int atMid = mountainArr.get(mid);

            if (target == atMid) {
                return mid;
            } else {
                if (isAscending) {
                    if (target > atMid) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    if (target < atMid) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }

        return -1;
    }
}
