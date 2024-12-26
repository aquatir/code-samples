package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 451. Sort Characters By Frequency
 * <p>
 * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character
 * is the number of times it appears in the string.
 * <p>
 * Return the sorted string. If there are multiple answers, return any of them.
 */
public class _451_SortCharactersByFrequency {


    public String frequencySortWithoutPairClass(String s) {
        // 1. calculate freqency map
        // 2. how to output result in correct order having the frequency?
        // priority queue will work

        var freq = new HashMap<Character, Integer>();
        for (var ch : s.toCharArray()) {
            freq.put(ch, 1 + freq.getOrDefault(ch, 0));
        }

        var pq = new PriorityQueue<Character>(
            (ch1, ch2) -> freq.get(ch2) - freq.get(ch1)
        );
        pq.addAll(freq.keySet());

        var res = new StringBuilder();

        while (!pq.isEmpty()) {
            var ch = pq.poll();
            for (int i = 0; i < freq.get(ch); i++) {
                res.append(ch);
            }
        }

        return res.toString();

    }

    class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public String frequencySort(String s) {
        // uppercase
        // lowercase
        // digits
        var counts = new HashMap<Character, Integer>();

        for (char c : s.toCharArray()) {
            var cur = counts.getOrDefault(c, 0);
            counts.put(c, cur + 1);
        }

        var pr = new PriorityQueue<Pair<Character, Integer>>(
            (v1, v2) -> Integer.compare(counts.getOrDefault(v2.getKey(), 0), counts.getOrDefault(v1.getKey(), 0))
        );
        for (var entry : counts.entrySet()) {
            pr.offer(new Pair<>(entry.getKey(), entry.getValue()));
        }

        var sb = new StringBuilder();
        while (!pr.isEmpty()) {
            var entry = pr.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }


        return sb.toString();
    }
}
