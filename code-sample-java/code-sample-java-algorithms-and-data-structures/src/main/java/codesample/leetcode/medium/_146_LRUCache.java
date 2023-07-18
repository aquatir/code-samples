package codesample.leetcode.medium;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU Cache — https://leetcode.com/problems/lru-cache/description/
 */
public class _146_LRUCache {

    class LRUCache {
        private int capacity;
        private LinkedHashMap<Integer, Integer> valueMap;


        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.valueMap = new LinkedHashMap<>(5, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            return valueMap.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            valueMap.put(key, value);
        }
    }
}
