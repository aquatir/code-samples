package codesample.leetcode.easy;

public class _392_IsSubsequence {
    static class Solution {

        // same as below, but a bit simpler
        public boolean isSubsequence(String s, String t) {
            // approach 1: 2 pointers
            //  move short (s) pointer when encounter the same elements in t in same order
            //  if all the elements of s are encountered by the end of t, return true
            //  else return false

            if (s.length() == 0) {
                return true;
            }

            int ptrShort = 0;
            int ptrLong = 0;

            while (ptrLong < t.length()) {
                if (s.charAt(ptrShort) == t.charAt(ptrLong)) {
                    ptrShort++;
                    if (ptrShort == s.length()) {
                        return true;
                    }
                }
                ptrLong++;
            }

            return false;
        }

        //        public boolean isSubsequence(String s, String t) {
//            int si = 0;
//            int ti = 0;
//
//            if (s.isEmpty()) {
//                return true;
//            }
//
//            // abc
//            // ahbgdc
//
//            // 1: si = 0, ti = 0 => a == a => 0 != 2 =>
//            // 2: si = 1, ti = 1 => b != h =>
//            // 3: si = 1, ti = 2 => b == b => 1 != 2 =>
//            // 4: si = 2, ti = 3 => c != g =>
//            // 5: si = 2, ti = 4 => c != d =>
//            // 6: si = 2, ti = 5 => c == c => 2 == s => exit with true;
//
//            while( si <= s.length() - 1 && ti <= t.length() - 1) {
//
//                if (s.charAt(si) == t.charAt(ti)) {
//                    si++;
//                    ti++;
//
//                    if (si == s.length()) {
//                        return true;
//                    }
//
//                } else {
//                    ti++;
//                }
//            }
//
//            return false;
//        }
//    }
    }

    public static void main(String[] args) {
        final var s = new Solution();
        System.out.println(s.isSubsequence("abc", "ahbgdc"));
    }
}
