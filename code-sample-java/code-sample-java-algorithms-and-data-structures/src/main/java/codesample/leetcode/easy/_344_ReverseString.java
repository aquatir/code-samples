package codesample.leetcode.easy;

/**
 * 344. Reverse String — https://leetcode.com/problems/reverse-string/description/
 */
public class _344_ReverseString {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            var tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
}
