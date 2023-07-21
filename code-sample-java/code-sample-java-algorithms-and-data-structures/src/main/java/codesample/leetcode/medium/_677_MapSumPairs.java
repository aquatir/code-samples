package codesample.leetcode.medium;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 677. Map Sum Pairs — https://leetcode.com/problems/map-sum-pairs/description/
 */
public class _677_MapSumPairs {

    class MapSum {

        class TrieNode {
            private char c;
            private int val;
            private Map<Character, TrieNode> children;

            TrieNode(char c) {
                this.c = c;
                this.val = 0;
                this.children = new HashMap<>();
            }

            public boolean hasChild(char c) {
                return children.containsKey(c);
            }


            public TrieNode getChild(char c) {
                return children.get(c);
            }

            public TrieNode craeteChildAndReturn(char c) {
                var node = new TrieNode(c);
                this.children.put(c, node);
                return node;
            }

            public void setVal(int val) {
                this.val = val;
            }

            public int getVal() {
                return this.val;
            }

            public Collection<TrieNode> getAllChildren() {
                return this.children.values();
            }
        }


        private TrieNode root;

        public MapSum() {
            this.root = new TrieNode(' ');
        }

        public void insert(String key, int val) {

            var cur = root;
            for (int i = 0; i < key.length(); i++) {
                var ch = key.charAt(i);
                if (cur.hasChild(ch)) {
                    cur = cur.getChild(ch);
                } else {
                    cur = cur.craeteChildAndReturn(ch);
                }
            }
            cur.setVal(val);
        }

        public int sum(String prefix) {
            var cur = root;
            var sum = 0;
            var prefixDoesntExist = false;
            for (int i = 0; i < prefix.length(); i++) {
                var ch = prefix.charAt(i);
                if (cur.hasChild(ch)) {
                    cur = cur.getChild(ch);
                } else {
                    prefixDoesntExist = true;
                    break;
                }
            }
            if (!prefixDoesntExist) {
                return countSumOfAllChildren(cur);
            } else {
                return 0;
            }
        }

        public int countSumOfAllChildren(TrieNode cur) {
            var sum = cur.getVal();
            for (TrieNode child : cur.getAllChildren()) {
                sum += countSumOfAllChildren(child);
            }
            return sum;
        }
    }
}
