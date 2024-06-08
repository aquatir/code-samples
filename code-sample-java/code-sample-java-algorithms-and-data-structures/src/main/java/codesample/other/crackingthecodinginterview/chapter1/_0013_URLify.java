package codesample.other.crackingthecodinginterview.chapter1;

import java.util.function.Function;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at
 * the end to hold the additional characters, and that yu are given the "true" length of the string.
 */
public class _0013_URLify {

    public String urlify(char[] charStr) {
        // approach 1: create an extra char[] to hold the resulting string
        // analyze initial string char-by-char. If it's a char => insert, if it's a space => insert %20 instead
        // this uses an extra string.length space and is O(n)

        // can we do it without extra space?
        // approach 2: we know that the string has "true" length, that is it will 100% fit the resulting string
        // we could start analyzing characters from the back. If it's a char => insert at the very end. If it's a space => insert %20 instead.
        // We are guaranteed to not override initial string due to "true" length property of input string

        // there could be legitimate whitespaces at the end of the string. How to measure the number of those?
        // 1. We can compute the number of spaces before the firstNonSpaceIndex as [numberOfSpacesBeforeEnd]
        // 2. Also compute the number of trailing spaces [numberOfSpacesAtTheEnd]
        // if (numberOfSpacesBeforeEnd * 3) == numberOfSpacesAtTheEnd; there are no extra whitespaces
        // else there are (numberOfSpacesAtTheEnd - (numberOfSpacesBeforeEnd * 3)) / 3 extra whitespace characters

        var firstNonSpaceIndex = charStr.length - 1;
        while (charStr[firstNonSpaceIndex] == ' ' && firstNonSpaceIndex != 0) {
            firstNonSpaceIndex--;
        }

        var numberOfSpacesBeforeEnd = 0;
        for (int i = 0; i < firstNonSpaceIndex; i++) {
            if (charStr[i] == ' ') {
                numberOfSpacesBeforeEnd++;
            }
        }
        var numberOfSpacesAtTheEnd = 0;
        for (int i = firstNonSpaceIndex; i <= charStr.length - 1; i++) {
            numberOfSpacesAtTheEnd++;
        }

        var numberOfTrailingSpacesInString = (numberOfSpacesAtTheEnd - (numberOfSpacesBeforeEnd * 3)) / 3;
        var nextFreeIndex = charStr.length - 1;
        for (int i = 0; i < numberOfTrailingSpacesInString; i++) {
            charStr[nextFreeIndex--] = '0';
            charStr[nextFreeIndex--] = '2';
            charStr[nextFreeIndex--] = '%';
        }

        if (nextFreeIndex == -1) {
            return new String(charStr);
        }

        for (int i = firstNonSpaceIndex; i >= 0; i--) {
            var charAt = charStr[i];
            if (charAt == ' ') {
                charStr[nextFreeIndex--] = '0';
                charStr[nextFreeIndex--] = '2';
                charStr[nextFreeIndex--] = '%';
                i -= 2; // move index by extra 2 positions because there were extra 2 writes
            } else {
                charStr[nextFreeIndex--] = charAt;
            }
        }

        return new String(charStr);
    }

    public static void main(String[] args) {
        var s = new _0013_URLify();

        Function<char[], String> func = s::urlify;

        System.out.println(func.apply("Mr John Smith    ".toCharArray())); // expected: Mr%20John%20Smith
        System.out.println(func.apply("      ".toCharArray())); // expected: %20%20
        System.out.println(func.apply("a      ".toCharArray())); // expected: a%20%20
        System.out.println(func.apply("      a".toCharArray())); // expected: %20%20a
        System.out.println(func.apply("a      a".toCharArray())); // expected: a%20%20a
    }
}
