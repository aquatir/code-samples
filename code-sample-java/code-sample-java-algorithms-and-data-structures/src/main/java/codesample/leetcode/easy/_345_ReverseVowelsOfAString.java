package codesample.leetcode.easy;

public class _345_ReverseVowelsOfAString {
    static class Solution {

        // "a" -> "a"
        // "ao" -> "oa"
        // "aoe" -> "eoa"
        public String reverseVowels(String s) {
            int left = 0;
            int right = s.length() - 1;
            char[] chars = s.toCharArray();

            while(left < right) {
                // find from left side
                while (left < right && !isVowel(chars[left])) {
                    left++;
                }
                while (left < right && !isVowel(chars[right])) {
                    right--;
                }
                // find from right side
                if (left < right) {
                    char tmp = chars[left];
                    chars[left] = chars[right];
                    chars[right] = tmp;
                }
                // swap

                // iterate after chars found
                left++;
                right--;
            }

            return new String(chars);
        }

        public boolean isVowel(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
        }
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.reverseVowels("hello")); // exp: holle
        System.out.println(s.reverseVowels("leetcode")); // exp: leotcede
        System.out.println(s.reverseVowels("a")); // exp: a
        System.out.println(s.reverseVowels("ao")); // exp: ao
        System.out.println(s.reverseVowels("aoe")); // exp: eoa
        System.out.println(s.reverseVowels("Aa")); // exp: aA
    }
}
