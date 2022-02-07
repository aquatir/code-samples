package codesample.other.educative._01_sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of characters where each character represents a fruit tree, you are given two baskets, and your
 * goal is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type of fruit.
 *
 * You can start with any tree, but you can’t skip a tree once you have started. You will pick one fruit from each tree
 * until you cannot, i.e., you will stop when you have to pick from a third fruit type.
 *
 * Write a function to return the maximum number of fruits in both baskets.
 *
 * Example 1
 * Input: Fruit=['A', 'B', 'C', 'A', 'C']
 * Output: 3
 * Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']
 *
 * Example 2
 * Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
 * Output: 5
 * Explanation: We can put 3 'B' in one basket and two 'C' in the other basket.
 * This can be done if we start with the second letter: ['B', 'C', 'B', 'B', 'C']
 */
public class _05_MaxFruitCountOf2Types {
    public static int findLength(char[] arr) {
        Map<Character, Integer> fruits = new HashMap<>();
        int windowStart = 0;

        int differentFruitsCurrently = 0;

        int currentMaxFruits = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            char fruit = arr[windowEnd];
            if (fruits.containsKey(fruit)) {
                fruits.put(fruit, fruits.get(fruit) + 1);
            } else {
                fruits.put(fruit, 1);
                differentFruitsCurrently++;
            }

            // remove excessive
            while (differentFruitsCurrently > 2) {
                char fruitAsStart = arr[windowStart];
                if (fruits.get(fruitAsStart) == 1) {
                    fruits.remove(fruitAsStart);
                    differentFruitsCurrently--;
                    windowStart++;
                } else {
                    fruits.put(fruitAsStart, fruits.get(fruitAsStart) - 1);
                    windowStart++;
                }
            }

            // count max
            currentMaxFruits = Math.max(currentMaxFruits, windowEnd - windowStart + 1);
        }

        return currentMaxFruits;
    }

    public static void main(String[] args) {
        System.out.println(_05_MaxFruitCountOf2Types.findLength(new char[] {'A', 'B', 'C', 'A', 'C'})); // exp: 3
        System.out.println(_05_MaxFruitCountOf2Types.findLength(new char[] {'A', 'B', 'C', 'B', 'B', 'C'})); // exp: 5
    }
}
