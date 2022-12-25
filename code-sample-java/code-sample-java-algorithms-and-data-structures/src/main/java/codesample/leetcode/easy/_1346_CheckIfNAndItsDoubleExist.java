package codesample.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 1346. Check If N and Its Double Exist — https://leetcode.com/problems/check-if-n-and-its-double-exist/description/
 */
public class _1346_CheckIfNAndItsDoubleExist {
    static class Solution {
        public boolean checkIfExist(int[] arr) {

            Set<Integer> searchingFor = new HashSet<>();

            for (int num: arr) {
                if (searchingFor.contains(num)) {
                    return true;
                } else {
                    searchingFor.add(num * 2);
                }
                if ((num % 2) == 0) { // if even
                    searchingFor.add(num / 2);
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.checkIfExist(new int[]{10,2,5,3}));       // expected = true
        System.out.println(s.checkIfExist(new int[]{4,-7,11,4,18}));   // expected == false
    }
}
