package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 208. Implement Trie (Prefix Tree) —https://leetcode.com/problems/implement-trie-prefix-tree/description/
 */
public class _208_Implement_Trie_PrefixTree {

    static class Trie {

        private TrieNode root;

        private class TrieNode {
            private char ch;
            private boolean endWord = false;
            private Map<Character, TrieNode> children;

            private TrieNode(char ch) {
                this.ch = ch;
                this.children = new HashMap<>();
            }

            // nullable
            public TrieNode childExist(char ch) {
                return children.getOrDefault(ch, null);
            }

            // append new node and return this node
            public TrieNode appendChild(char ch) {
                var newChild = new TrieNode(ch);
                this.children.put(ch, newChild);
                return newChild;
            }

            // mark node as end of work
            public void markEndWord() {
                this.endWord = true;
            }

            public boolean isWordEnd() {
                return this.endWord;
            }
        }

        public Trie() {
            // initialize with root of empty string
            this.root = new TrieNode(' ');
        }

        public void insert(String word) {
            var cur = root;
            for (int i = 0; i < word.length(); i++) {
                var ch = word.charAt(i);

                var childExist = cur.childExist(ch);
                if (childExist != null) {
                    cur = childExist;
                } else {
                    cur = cur.appendChild(ch);
                }
            }
            cur.markEndWord();
        }

        public boolean startsWith(String prefix) {
            return searchAndReturnLastNode(prefix) != null;
        }

        public boolean search(String word) {
            var lastNode = searchAndReturnLastNode(word);
            return lastNode != null && lastNode.isWordEnd();
        }

        public TrieNode searchAndReturnLastNode(String word) {
            var cur = root;

            for (int i = 0; i < word.length(); i++) {
                var ch = word.charAt(i);
                var childExist = cur.childExist(ch);
                if (childExist == null) {
                    return null;
                } else {
                    cur = childExist;
                }
            }
            return cur;
        }
    }

    public static void main(String[] args) {
        /*
        ["Trie","insert","search","search","startsWith","insert","search"]
        [[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
         */

        var trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // exp: true
        System.out.println(trie.search("app"));     // exp: false
        System.out.println(trie.startsWith("app"));       // exp: true
        trie.insert("app");
        System.out.println(trie.search("app"));     // exp: true
    }
}
