package codesample.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 784. Letter Case Permutation — https://leetcode.com/problems/letter-case-permutation/
 * <p>
 * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
 * <p>
 * Return a list of all possible strings we could create. Return the output in any order.
 */
public class _784_LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        int maxVariablePermutes = 0;
        List<Integer> indexesOfPermutatableChars = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                indexesOfPermutatableChars.add(i);
            }
        }

        if (indexesOfPermutatableChars.size() == 0) {
            result.add(s);
            return result;
        }


        // Appraoch 1: now create all permutations;
        //  create a queue of all permutations
        //  on each new char => for all in queue
        //     => extract, apply, store
        //  until finished with last index
        // at this point queue is filled with strings which have all permutations applied...?
        Queue<String> currentPerms = new ArrayDeque<>();
        currentPerms.offer(s);

        for (int i: indexesOfPermutatableChars) {
            int curPermsSize = currentPerms.size();

            for (int j = 0; j < curPermsSize; j++) {
                String perm = currentPerms.poll();
                var lowerString = charToLower(perm, i);
                var higherString = charToHigher(perm, i);

                currentPerms.offer(lowerString);
                currentPerms.offer(higherString);
            }
        }

        return new ArrayList<>(currentPerms);

        // Approach 2: create permutation from 1 char only.
        //  on each new step, either just add digit to all or add digit + current in two cases.
    }

    public String charToLower(String s, int index) {
        var targetCharLower = Character.toLowerCase(s.charAt(index));

        StringBuilder myName = new StringBuilder(s);
        myName.setCharAt(index, targetCharLower);
        return myName.toString();
    }

    public String charToHigher(String s, int index) {
        var targetCharHigher = Character.toUpperCase(s.charAt(index));

        StringBuilder myName = new StringBuilder(s);
        myName.setCharAt(index, targetCharHigher);
        return myName.toString();
    }
}
