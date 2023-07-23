package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 648. Replace Words — https://leetcode.com/problems/replace-words/description/
 */
public class _648_ReplaceWords {

    private class TrieNode {
        private boolean rootFinish; // whatever this Node is a root finish
        private Map<Character, TrieNode> children;

        public TrieNode() {
            this.children = new HashMap<>();
            this.rootFinish = false;
        }

        public void markAsFinish() {
            this.rootFinish = true;
        }

        public boolean isFinish() {
            return this.rootFinish;
        }

        public boolean hasChild(char ch) {
            return this.children.containsKey(ch);
        }

        public TrieNode getChild(char ch) {
            return this.children.get(ch);
        }

        public TrieNode newChild(char ch) {
            var newNode = new TrieNode();
            this.children.put(ch, newNode);
            return newNode;
        }
    }


    private class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void add(String word) {
            var cur = root;

            for (int i = 0; i < word.length(); i++) {
                var ch = word.charAt(i);
                if (cur.hasChild(ch)) {
                    cur = cur.getChild(ch);
                } else {
                    cur = cur.newChild(ch);
                }
            }
            cur.markAsFinish();
        }

        // nullable
        public String findShortestReplacement(String word) {
            var cur = root;

            for (int i = 0; i < word.length(); i++) {
                var ch = word.charAt(i);

                if (cur.hasChild(ch)) {
                    cur = cur.getChild(ch);
                    if (cur.isFinish()) {
                        return word.substring(0, i + 1); // + 1 ?
                    }
                } else {
                    return null;
                }
            }
            return null;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        var trie = new Trie();

        // given a word, I need to find if ANY prefix exists in a trie
        // if yes => replace
        // do for each word

        for (var word : dictionary) {
            trie.add(word);
        }

        var res = new StringBuilder();
        var wordsInSentence = sentence.split(" ");

        for (var word : wordsInSentence) {
            var mbReplacement = trie.findShortestReplacement(word);
            if (mbReplacement != null) {
                res.append(mbReplacement);
                res.append(' ');
            } else {
                res.append(word);
                res.append(' ');
            }
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1); // delete trailing ' '
        }

        return res.toString();
    }
}
