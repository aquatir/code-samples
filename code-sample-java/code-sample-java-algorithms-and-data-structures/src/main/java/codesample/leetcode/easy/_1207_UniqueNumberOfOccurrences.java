package codesample.leetcode.easy;

/**
 * 1207. Unique Number of Occurrences — https://leetcode.com/problems/unique-number-of-occurrences/description/
 */
public class _1207_UniqueNumberOfOccurrences {
    public boolean uniqueOccurrences(int[] arr) {

        // number => number of occurences
        var map = new HashMap<Integer, Integer>();

        for (var num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        var encounteredValues = new HashSet<Integer>();
        for (var value : map.values()) {
            if (encounteredValues.contains(value)) {
                return false;
            } else {
                encounteredValues.add(value);
            }
        }

        return true;
    }
}
