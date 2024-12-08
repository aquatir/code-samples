package codesample.leetcode.easy;

/**
 * 557. Reverse Words in a String III — https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
 */
public class _557_ReverseWordsInAStringIII {

    // Approach 1: find a word (aka find space ' ') => reverse this word, continue;
    public String reverseWords(String s) {
        var result = new StringBuilder();
        var wordStart = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                var revertedWord = revertWord(s, wordStart, i);
                if (!revertedWord.isBlank()) {
                    result.append(revertedWord);
                    result.append(" ");
                }
                wordStart = i + 1;
            }
        }

        result.append(revertWord(s, wordStart, s.length()));
        return result.toString();
    }

    private String revertWord(String wholeString, int from, int to) {
        var trimmed = wholeString.substring(from, to).trim();
        return new StringBuilder(trimmed).reverse().toString();
    }

    // Approach 2: reverse all characters, then reverse words order;
    public String reverseWordsViaCharacters(String s) {
        var n = s.length();
        var asCharArr = s.toCharArray();
        var left = 0;
        var right = n-1;

        // reverse all characters
        while (left < right) {
            var tmp = asCharArr[left];
            asCharArr[left] = asCharArr[right];
            asCharArr[right] = tmp;
            left++;
            right--;
        }

        // reverse all words
        var reversedString = new String(asCharArr);
        String[] words = reversedString.split(" ");

        left = 0;
        right = words.length - 1;
        while (left < right) {
            var tmp = words[left];
            words[left] = words[right];
            words[right] = tmp;
            left++;
            right--;
        }

        var answer = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            answer.append(words[i]);
            if (i != words.length - 1) {
                answer.append(" ");
            }
        }

        return answer.toString();
    }
}
