package codesample.other.crackingthecodinginterview.chapter1;

import java.util.HashMap;

/**
 * Given a string, write a function to check if it's a permutation of a palindrome. A palindrome is a word or phrase that is the same
 * forwards and backwards. A permutations is a rearrangement of letters. The Palindrome does not need to be limited to
 * just dictionary words
 */
public class _0014_PalindromePermutation {
    public boolean isPalindromePermutation(String str) {
        // Can ignore whitespace characters
        // Can ignore cases
        // If we count the frequency of each character, a string is guaranteed to be a palindrome if
        // - all characters have an even frequency
        // - 1 character have an odd frequency, the rest have an even frequency

        // Algorithm: count frequency of each character, ignoring cases and spaces
        // count number of char->freq, where frequency is odd. When it hits two => return false. If it stays at 0 or 1
        // by the end => return true

        var freq = new HashMap<Character, Integer>();
        var lowerCase = str.toLowerCase();

        for (char ch: lowerCase.toCharArray()) {
            if (Character.isAlphabetic(ch))  {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
        }

        var numOfEvenFreq = 0;

        for (var freqOfCharacter: freq.values()) {
            if (freqOfCharacter % 2 == 1) {
                numOfEvenFreq++;
            }
            if (numOfEvenFreq > 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        var s = new _0014_PalindromePermutation();

        System.out.println(s.isPalindromePermutation("Tact Coa"));  // true
        System.out.println(s.isPalindromePermutation("aabb"));      // true
        System.out.println(s.isPalindromePermutation("aabbc"));     // true
        System.out.println(s.isPalindromePermutation("aabbccc"));   // true
        System.out.println(s.isPalindromePermutation("aabblm"));    // false
        System.out.println(s.isPalindromePermutation("  aba  "));   // true
        System.out.println(s.isPalindromePermutation("  ab  "));    // false
    }
}
