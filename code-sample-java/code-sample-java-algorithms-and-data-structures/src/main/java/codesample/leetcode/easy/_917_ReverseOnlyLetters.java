package codesample.leetcode.easy;

/**
 * 917. Reverse Only Letters https://leetcode.com/problems/reverse-only-letters/description/
 */
public class _917_ReverseOnlyLetters {
    public String reverseOnlyLetters(String s) {
        // Approach: 2 pointers. Continue each pointer until reversable character is met, reverse
        // keep doing until pointers match

        var chars = s.toCharArray();
        var left = 0;
        var right = chars.length - 1;

        while (left < right) {
            // move left until it points to English char
            while (left != chars.length && !isEnglishChar(chars[left])) {
                left++;
            }
            // move right until it points to English char
            while (right != -1 && !isEnglishChar(chars[right])) {
                right--;
            }
            if (left >= right) {
                break;
            }

            var tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }

        return new String(chars);
    }

    private boolean isEnglishChar(char c) {
        return c >= 'a' && c <= 'z'
            || c >= 'A' && c <= 'Z';
    }
}
