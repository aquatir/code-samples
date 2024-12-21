package codesample.leetcode.medium;

import javax.sql.rowset.serial.SerialStruct;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. Group Anagrams — https://leetcode.com/problems/group-anagrams/description/
 */
public class _49_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        // use sorter word as a key
        var freq = new HashMap<String, List<String>>();

        for (var str: strs) {
            var charArray = str.toCharArray();
            Arrays.sort(charArray);
            var key = String.valueOf(charArray);

            if (!freq.containsKey(key)) {
                freq.put(key, new ArrayList<String>());
            }
            freq.get(key).add(str);
        }

        return new ArrayList(freq.values());
    }

    public List<List<String>> groupAnagramsTrivial(String[] strs) {
        // for each word count word frequency individually
        // group equal maps together

        // use whole frequency map as a key for hashmap
        var freqToWord = new HashMap<Map<Character, Integer>, List<String>>();
        for (String str: strs) {
            var freqMap = countCharFrequency(str);
            if (freqToWord.containsKey(freqMap)) {
                var curList = freqToWord.get(freqMap);
                curList.add(str);
            } else {
                var newList = new ArrayList<String>();
                newList.add(str);
                freqToWord.put(freqMap, newList);
            }
        }

        return new ArrayList<>(freqToWord.values());
    }

    private Map<Character, Integer> countCharFrequency(String str) {
        var freq = new HashMap<Character, Integer>();
        for (var ch: str.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        return freq;
    }
}
