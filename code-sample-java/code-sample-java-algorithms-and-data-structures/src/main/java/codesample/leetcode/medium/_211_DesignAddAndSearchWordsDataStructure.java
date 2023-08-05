package codesample.leetcode.medium;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 211. Design Add and Search Words Data Structure — https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
 */
public class _211_DesignAddAndSearchWordsDataStructure {

    class WordDictionary {

        class TrieNode {
            private Map<Character, TrieNode> children;
            private boolean isWord;

            public TrieNode() {
                this.children = new HashMap<>();
                this.isWord = false;
            }

            public void setIsWord() {
                this.isWord = true;
            }

            public boolean isWord() {
                return this.isWord;
            }

            public boolean hasChild(char ch) {
                return children.containsKey(ch);
            }

            public TrieNode getChild(char ch) {
                return children.get(ch);
            }

            public TrieNode createChild(char ch) {
                var newNode = new TrieNode();
                this.children.put(ch, newNode);
                return newNode;
            }

            public Collection<TrieNode> children() {
                return children.values();
            }
        }

        class Trie {
            private TrieNode root = new TrieNode();

            public void addWord(String word) {
                var cur = root;
                for (int i = 0; i < word.length(); i++) {
                    var ch = word.charAt(i);
                    if (cur.hasChild(ch)) {
                        cur = cur.getChild(ch);
                    } else {
                        cur = cur.createChild(ch);
                    }
                }
                cur.setIsWord();
            }

            public boolean search(String word) {
                return searchRecursive(root, word);
            }

            private boolean searchRecursive(TrieNode node, String word) {
                var cur = node;
                for (int i = 0; i < word.length(); i++) {
                    var ch = word.charAt(i);
                    if (ch != '.') {
                        if (cur.hasChild(ch)) {
                            cur = cur.getChild(ch);
                        } else {
                            return false;
                        }
                    } else {
                        // run recursive search for ALL nodes
                        // this should not evaluate predicate on all children, so should be effective
                        int finalI = i;
                        return cur.children().stream().anyMatch(child -> searchRecursive(child, word.substring(finalI + 1)));
                    }

                }
                return cur.isWord;
            }
        }

        private final Trie trie;

        public WordDictionary() {
            this.trie = new Trie();
        }

        public void addWord(String word) {
            trie.addWord(word);
        }

        public boolean search(String word) {
            return trie.search(word);
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
