package codesample.leetcode.easy;

/**
 * 746. Min Cost Climbing Stairs — https://leetcode.com/problems/min-cost-climbing-stairs/
 * <p>
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
 * You can either start from the step with index 0, or the step with index 1.
 * Return the minimum cost to reach the top of the floor.
 */
public class _746_MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 1) {
            return cost[0];
        }
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }

        int[] minCostsToGetHere = new int[cost.length];

        /* How much does it cost to GET to step.
            Note: we only pay for getting out when we step out.
        */
        minCostsToGetHere[0] = 0;
        minCostsToGetHere[1] = 0;

        for (int i = 2; i< cost.length; i++) {
            minCostsToGetHere[i] = Math.min(
                    minCostsToGetHere[i-2] + cost[i-2],
                    minCostsToGetHere[i-1] + cost[i-1]
            );
        }

        return Math.min(
                minCostsToGetHere[cost.length - 1] + cost[cost.length - 1],
                minCostsToGetHere[cost.length - 2] + cost[cost.length - 2]
        );
    }

    public int minCostClimbingStairsNoArray(int[] cost) {
        if (cost.length == 1) {
            return cost[0];
        }
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }

        int[] minCostsToGetHere = new int[cost.length];

        /* How much does it cost to GET to step.
            Note: we only pay for getting out when we step out.
        */
        int minCostsToGetTwoStepBack = 0;
        int minCostsToGetOneStepBack = 0;

        for (int i = 2; i< cost.length; i++) {
            int nextCheapestStep = Math.min(
                    minCostsToGetTwoStepBack + cost[i-2],
                    minCostsToGetOneStepBack + cost[i-1]
            );
            minCostsToGetTwoStepBack = minCostsToGetOneStepBack;
            minCostsToGetOneStepBack = nextCheapestStep;
        }

        return Math.min(
                minCostsToGetOneStepBack + cost[cost.length - 1],
                minCostsToGetTwoStepBack + cost[cost.length - 2]
        );
    }
}
