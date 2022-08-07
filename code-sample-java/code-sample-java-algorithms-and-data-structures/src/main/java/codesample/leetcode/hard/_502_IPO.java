package codesample.leetcode.hard;

import java.util.PriorityQueue;

/**
 * 502. IPO — https://leetcode.com/problems/ipo/
 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 * <p>
 * You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
 * <p>
 * Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 * <p>
 * Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
 * <p>
 * The answer is guaranteed to fit in a 32-bit signed integer.
 *
 * TODO: make optimal solution... with minheap + maxheap ??
 */
public class _502_IPO {

    class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private K getKey() {
            return key;
        }
        private V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        /**
         k — number of porjects
         w — initial capital
         profits — profit of each project
         capital — capital required for each project

         We need to gain max profits on each project. The bigger the profit the better, capital doesn't matter if we can afford it, e.g. profit/capital 5/1 and 5/5 is the same if our capital is exactly 5. We can order those with maxHeap

         As for the capital, we can only pick project which we can afford, e.g. where w >= capital[i].
         We can order by capital with minHeap, but low capital != low profits, so it's not super clear which projects to remove...

         Step 1: find the list of affordable projects
         Step 2: find the most profitable out of those with index i
         Step 3: add w += profits[i]
         Step 4: k--; repeat until k == 0;
         */

        // approach 1: brute force — Time Limit Exceeded
//         int n = profits.length;
//         while (k != 0) {
//             int bestProjectProfits = -1;
//             int bestProjectIndex = 0;
//             for (int i = 0; i < n; i++) {
//                 if (capital[i] > w) {
//                     continue;
//                 } else {
//                     if (profits[i] > bestProjectProfits) {
//                         bestProjectProfits = profits[i];
//                         bestProjectIndex = i;
//                     }

//                 }
//             }
//             // if no project bring value => break from the loop
//             if (bestProjectProfits == -1) {
//                 break;
//             }
//             // when project is used ones => never reuse it by setting profits to -1
//             profits[bestProjectIndex] = -1;
//             k--;
//             w += bestProjectProfits;
//         }

//         return w;

        // approach 2:
        // two priority queues.
        // One: max-heap of AFFORDABLE projects by profit (always take max profit)
        // this will hold all projects where capital <= available (w)
        // Two: min-heap of NON-AFFORDABLE projects by capital (always take min profit)
        // this will hold all projects where capital > availble (w)
        // when new project is finished from AFFORDABLE, we move values from NON-AFFORDABLE to AFFORDABLE


        int n = profits.length;
        int currentCapital = w;
        int numOfProjectsLeft = Math.min(k, n);

        // we could also use Pair class here, but this is faster because no pair is created
        // index -> profit
        var pqMaxAffordableByProfit = new PriorityQueue<Integer>(
            (one, two) -> Integer.compare(profits[two], profits[one])
        );
        // index -> capital
        var pqMinNonAffordableByCapital = new PriorityQueue<Integer>(
            (one, two) -> Integer.compare(capital[one], capital[two])
        );

        // split between affordable/non-affordable
        for (int i = 0; i < n; i++) {
            if (capital[i] > currentCapital) {
                pqMinNonAffordableByCapital.offer(i);
            } else {
                pqMaxAffordableByProfit.offer(i);
            }
        }

        // while there are affordable projects
        //  -> 1. add the value of affordable to capital and remove from affordable
        //  -> 2. add all projects which are now affordable into affordable list
        while (!pqMaxAffordableByProfit.isEmpty() && numOfProjectsLeft != 0) {
            // get max profit from affordable projects
            currentCapital += profits[pqMaxAffordableByProfit.poll()];

            while(!pqMinNonAffordableByCapital.isEmpty() && currentCapital >= capital[pqMinNonAffordableByCapital.peek()]) {
                pqMaxAffordableByProfit.offer(pqMinNonAffordableByCapital.poll());
            }

            numOfProjectsLeft--;
        }

        return currentCapital;
    }

    public static void main(String[] args) {
        var s = new _502_IPO();
        System.out.println(s.findMaximizedCapital(2, 0, new int[] {1,2,3}, new int[] {0,1,1} )); // expected 4
        System.out.println(s.findMaximizedCapital(3, 0, new int[] {1,2,3}, new int[] {0,1,2} )); // expected 6
    }
}
