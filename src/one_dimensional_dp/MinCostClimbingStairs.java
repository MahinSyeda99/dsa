package one_dimensional_dp;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/description/
 */
public class MinCostClimbingStairs {

    //O(n) TC
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        //Create a dp array
        int[] dp = new int[n];
        //Initialize the values of cost for first two stairs to reach 3rd stair
        dp[0] = cost[0];
        dp[1] = cost[1];
        //For the remaining steps, we just choose the minimum of costs of climbing prev one stair and two stairs
        //Using bottom up approach
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }

    public static void main(String[] args) {
        MinCostClimbingStairs costClimbingStairs = new MinCostClimbingStairs();
        int[] cost = {10, 15, 20};
        //o/p is 15
        System.out.println(costClimbingStairs.minCostClimbingStairs(cost));
        cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        //o/p is 6
        System.out.println(costClimbingStairs.minCostClimbingStairs(cost));
    }
}
