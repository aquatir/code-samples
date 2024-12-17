package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 2225. Find Players With Zero or One Losses — https://leetcode.com/problems/find-players-with-zero-or-one-losses/description/
 */
public class _2225_FindPlayersWithZeroOrOneLosses {
    public List<List<Integer>> findWinners(int[][] matches) {
        // approach 1: create 3 sets (0, 1, more loses), move elements between them?
        // approach 2: count number of loses for each player with frequency map

        // player id to number of loses
        var freqLoses = new HashMap<Integer, Integer>();
        for (int[] game: matches) {
            var winner = game[0];
            var loser = game[1];

            freqLoses.put(winner, freqLoses.getOrDefault(winner, 0));
            freqLoses.put(loser, freqLoses.getOrDefault(loser, 0) + 1);
        }

        var winnersList = new ArrayList<Integer>();
        var oneLoseList = new ArrayList<Integer>();

        for (var entry: freqLoses.entrySet()) {
            if (entry.getValue() == 0) {
                winnersList.add(entry.getKey());
            } else if (entry.getValue() == 1) {
                oneLoseList.add(entry.getKey());
            }
        }

        Collections.sort(winnersList);
        Collections.sort(oneLoseList);

        return List.of(winnersList, oneLoseList);
    }
}
