package codesample.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 151. Reverse Words in a String — https://leetcode.com/problems/reverse-words-in-a-string/description/
 */
public class _151_ReverseWordsInAString {

//    public String reverseWords(String s) {
//
//        // revert whole string => revert each word in string => result
//        var revertedString = new StringBuilder(s.trim()).reverse().toString();
//        var result = new StringBuilder();
//
//        var wordStart = 0;
//        for (int i = 0; i < revertedString.length(); i++) {
//            if (revertedString.charAt(i) == ' ') {
//                var revertedWord = revertWord(revertedString, wordStart, i);
//                if (!revertedWord.isBlank()) {
//                    result.append(revertedWord);
//                    result.append(" ");
//                }
//                wordStart = i + 1;
//            }
//        }
//
//        result.append(revertWord(revertedString, wordStart, revertedString.length()));
//        return result.toString();
//    }
//
//    private String revertWord(String wholeString, int from, int to) {
//        var trimmed = wholeString.substring(from, to).trim();
//        return new StringBuilder(trimmed).reverse().toString();
//    }

    // split & reverse with library functions
//    public String reverseWords(String s) {
//        // remove leading spaces
//        s = s.trim();
//
//        // split by multiple spaces
//        var wordList = Arrays.asList(s.split("\\s+"));
//
//        // reverse
//        Collections.reverse(wordList);
//
//        // join
//        return String.join(" ", wordList);
//    }

    // split & reverse without library functions
//    public String reverseWords(String s) {
//        String[] words = s.trim().split(" ");
//        StringBuilder result = new StringBuilder();
//        for (int i = words.length - 1; i >= 0; i--) {
//            if (!words[i].isEmpty()) {
//                result.append(words[i]).append(" ");
//            }
//        }
//        return result.toString().trim();
//    }

    // use deque to reverse
    // could also use a stack, iterate from left to right, then start popping resulting in right=>left order
    public String reverseWords(String s) {
        int left = 0;
        int right = s.length() - 1;

        // remove leading & trailing spaces
        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }
        while (left <= right && s.charAt(right) == ' ') {
            right--;
        }

        Deque<String> deque = new ArrayDeque<>();

        // push word by word in front of deque
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                deque.addFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            left++;
        }
        // push the last work because it won't be pushed automatically
        deque.addFirst(word.toString());

        // this could be done without String.join by iterating with removeFirst
        return String.join(" ", deque);
    }
}
