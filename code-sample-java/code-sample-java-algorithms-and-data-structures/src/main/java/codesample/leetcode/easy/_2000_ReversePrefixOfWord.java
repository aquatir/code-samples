package codesample.leetcode.easy;

/**
 * 2000. Reverse Prefix of Word https://leetcode.com/problems/reverse-prefix-of-word/description/
 */
public class _2000_ReversePrefixOfWord {
    public String reversePrefix(String word, char ch) {
        var index = -1;
        var chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ch) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return word;
        }

        var left = 0;
        var right = index;
        while (left < right) {
            var tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }

        return new String(chars);
    }
}
