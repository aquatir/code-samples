package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 1657. Determine if Two Strings Are Close — https://leetcode.com/problems/determine-if-two-strings-are-close/description/
 */
public class _1657_DetermineIfTwoStringsAreClose {
    public boolean closeStrings(String word1, String word2) {
        // approach 1: compare by first and by second as two distinct operations?
        // when change every occurance will help? When freq of elements will require a single change
        // >>> You can use the operations on either string as many times as necessary.

        // e.g. word1 a:1, b:3, word2: a:3, b:1
        if (word1.length() != word2.length()) {
            return false;
        }

        // we must count frequencies first
        // if swapping 2 frequencies is enough => we are good
        // if we may also swap characters via frequencies, because the order is irrelevant
        // we get 2 freq maps: can we make one out of the other?

        var freq1 = new HashMap<Character, Integer>();
        var freq2 = new HashMap<Character, Integer>();

        for (int i = 0; i < word1.length(); i++) {
            var char1 = word1.charAt(i);
            var char2 = word2.charAt(i);

            freq1.put(char1, freq1.getOrDefault(char1, 0) + 1);
            freq2.put(char2, freq2.getOrDefault(char2, 0) + 1);
        }

        // map must have the same number of keys => if not we have to swap by summing some frequences together
        // when swapping, what should we add where?
        // can first start with counting discrepansies. E.g. remove the characters that match already by value&frequency. We will then only need to deal with smaller subset of "not-matching-characters".

        // can not just remove. Must count & remove because iteration. Or use an iterator

        // every character in word1 must exist in word2

        if (!freq1.keySet().equals(freq2.keySet())) {
            return false;
        }

        // now for frequencies, there must be the "same amount" of frequencies even of different characters. We do that by sorting and analyzing the frequencies together
        var word1FreqList = new ArrayList<>(freq1.values());
        var word2FreqList = new ArrayList<>(freq2.values());
        Collections.sort(word1FreqList);
        Collections.sort(word2FreqList);
        return word1FreqList.equals(word2FreqList);
    }
}
