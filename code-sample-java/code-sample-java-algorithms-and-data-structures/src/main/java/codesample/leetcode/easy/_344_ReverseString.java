package codesample.leetcode.easy;

/**
 * 344. Reverse String — https://leetcode.com/problems/reverse-string/description/
 */
public class _344_ReverseString {

    // Iterative
//    public void reverseString(char[] s) {
//        int left = 0;
//        int right = s.length - 1;
//
//        while (left < right) {
//            var tmp = s[left];
//            s[left] = s[right];
//            s[right] = tmp;
//            left++;
//            right--;
//        }
//    }

    // recursive
    public void reverseString(char[] s) {
        reverseString(s, 0, s.length - 1);
    }

    public void reverseString(char[] s, int from, int to) {
        // base case: from >= to => do nothing & return
        if (from < to) {
            var tmp = s[from];
            s[from] = s[to];
            s[to] = tmp;
            reverseString(s, from + 1, to - 1);
        }
    }
}
