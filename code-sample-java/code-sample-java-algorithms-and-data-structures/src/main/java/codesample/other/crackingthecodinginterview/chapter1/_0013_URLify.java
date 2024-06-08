package codesample.other.crackingthecodinginterview.chapter1;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at
 * the end to hold the additional characters, and that yu are given the "true" length of the string.
 */
public class _0013_URLify {

    public String urlify(char[] charStr, int trueLength) {
        // approach 1: create an extra char[] to hold the resulting string
        // analyze initial string char-by-char. If it's a char => insert, if it's a space => insert %20 instead
        // this uses an extra string.length space and is O(n)

        // can we do it without extra space?
        // approach 2: we know that the string has "true" length, that is it will 100% fit the resulting string
        // we could start analyzing characters from the back. If it's a char => insert at the very end. If it's a space => insert %20 instead.
        // We are guaranteed to not override initial string due to "true" length property of input string

        // there could be legitimate whitespaces at the beginning or end of the string, must think about them too

        // This here assumes that (charStr) might hold MORE spaces than required. If this is the case, we have to compute
        // the first insert positions by
        // 1. counting (trueNumberOfSpaces)
        // 2. set the (insertIndex) to (trueLength - 1 + 2 * trueNumberOfSpaces)
        // otherwise, if (charStr) has exactly enough characters, we may set (insertIndex) to (charStr.length - 1)
        var trueNumberOfSpaces = 0;
        for (int i = 0; i < trueLength; i++) {
            if (charStr[i] == ' ') {
                trueNumberOfSpaces++;
            }
        }
        var insertIndex = (trueLength - 1) + 2 * trueNumberOfSpaces;
        var trueIndex = trueLength - 1;

        for (int i = trueIndex; i >= 0; i--) {
            if (charStr[i] == ' ') {
                charStr[insertIndex--] = '0';
                charStr[insertIndex--] = '2';
                charStr[insertIndex--] = '%';
            } else {
                charStr[insertIndex--] = charStr[i];
            }
        }

        return new String(charStr);
    }

    public static void main(String[] args) {
        var s = new _0013_URLify();

        BiFunction<char[], Integer, String> func = s::urlify;

        System.out.println(func.apply("Mr John Smith    ".toCharArray(), 13)); // expected: Mr%20John%20Smith
        System.out.println(func.apply("      ".toCharArray(), 2)); // expected: %20%20
        System.out.println(func.apply("a      ".toCharArray(), 3)); // expected: a%20%20
        System.out.println(func.apply("  a    ".toCharArray(), 3)); // expected: %20%20a
        System.out.println(func.apply("a  a    ".toCharArray(), 4)); // expected: a%20%20a

        // this test cases check that we don't use extra space even if it's available
        System.out.println(func.apply("Mr John Smith       ".toCharArray(), 13)); // expected: Mr%20John%20Smith
    }
}
