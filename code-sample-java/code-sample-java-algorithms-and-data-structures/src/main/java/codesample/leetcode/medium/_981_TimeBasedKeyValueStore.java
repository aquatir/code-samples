package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 981. Time Based Key-Value Store  — https://leetcode.com/problems/time-based-key-value-store/
 *
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 *
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 */
public class _981_TimeBasedKeyValueStore {

    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */
    class TimeMap {

        private HashMap<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (map.get(key) == null) {
                TreeMap<Integer, String> innerMap = new TreeMap<>();
                innerMap.put(timestamp, value);
                map.put(key, innerMap);
            } else {
                TreeMap<Integer, String> innerMap = map.get(key);
                innerMap.put(timestamp, value);
            }
        }

        public String get(String key, int timestamp) {
            if (map.get(key) == null) {
                return "";
            } else {
                final var innerMapEntry = map.get(key).floorEntry(timestamp);
                if (innerMapEntry == null) {
                    return "";
                } else {
                    return innerMapEntry.getValue();
                }

            }
        }
    }
}
