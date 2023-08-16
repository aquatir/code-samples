package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 421. Maximum XOR of Two Numbers in an Array — https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class _421_MaximumXOROfTwoNumbersInAnArray {

    // Brute-force: Time limit exceeded
//    public int findMaximumXOR(int[] nums) {
//
//        // brute force
//        int max = 0;
//
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                max = Math.max(max, nums[i] ^ nums[j]);
//            }
//        }
//
//        return max;
//    }

    // using bitwise Trie

    // max xor == two elements with the least amount of overlapping ones in binary representation
    // can build a TRIE using binary representations where the node will hold 1/0 and child will either be 1 or 0.
    //      How to compute the best using this TRIE?
    //
    // [010, 101]
    // TRIE:
    // -
    // 0 1
    // 1 0
    // 0 1
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();

        public TrieNode() {
        }
    }


    public int findMaximumXOR(int[] nums) {

        // the max number is definitely going to be part of the multiplication
        int maxNum = nums[0];
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        int maxBitLength = (Integer.toBinaryString(maxNum)).length();

        // zero left-padding to ensure L bits for each number
        int n = nums.length;
        int bitmask = 1 << maxBitLength;
        String[] strNums = new String[n];
        for (int i = 0; i < n; ++i) {
            strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
        }

        TrieNode trie = new TrieNode();
        int maxXor = 0;
        for (String num : strNums) {
            TrieNode node = trie;
            TrieNode xorNode = trie;
            int currXor = 0;
            for (Character bit : num.toCharArray()) {
                // insert new number in trie
                if (node.children.containsKey(bit)) {
                    node = node.children.get(bit);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(bit, newNode);
                    node = newNode;
                }

                // compute max xor of that new number
                // with all previously inserted
                Character toggledBit = bit == '1' ? '0' : '1';
                if (xorNode.children.containsKey(toggledBit)) {
                    currXor = (currXor << 1) | 1;
                    xorNode = xorNode.children.get(toggledBit);
                } else {
                    currXor = currXor << 1;
                    xorNode = xorNode.children.get(bit);
                }
            }
            maxXor = Math.max(maxXor, currXor);
        }

        return maxXor;
    }
}
