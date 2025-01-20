package two_dimensional_dp;

import java.util.Arrays;

public class BurstBalloons {

    //Solution 2
    //2D DP Bottom Up solution - matrix multiplication pattern
    //O(n^3) TC and O(n^2) SC
    public int maxCoins2(int[] nums) {
        int n = nums.length;
        //dp[i][j] represents coins obtained from bursting all balloons between index i and index j (inclusive)
        int[][] dp = new int[n][n];

        //We start with length 1 and go up to full length
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                //If k is the index of the last balloon burst in (i, j), the coins that burst will get are nums[i] * nums[k] * nums[j], and to calculate dp[i][j], we also need to add the coins obtained from bursting balloons between i and k, and between k and j, i.e., dp[i][k] and dp[k][j]
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], (i - 1 >= 0 ? nums[i - 1] : 1) * nums[k] * (j + 1 < nums.length ? nums[j + 1] : 1) + (k > 0 ? dp[i][k - 1] : 0) + (k + 1 < n ? dp[k + 1][j] : 0));
                }
            }
        }
        return dp[0][n - 1];
    }

    //Solution 1
    //2D DP Top Down solution - matrix multiplication pattern
    //O(n^3) TC and O(n^2) SC
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return maxCoinsH(nums, 0, nums.length - 1, dp);
    }

    private int maxCoinsH(int[] nums, int i, int j, int[][] dp) {
        //Base condition
        if (i > j) {
            return 0;
        }
        //Base condition - Only one element exists
        if (i == j) {
            return ((i - 1 >= 0 ? nums[i - 1] : 1) * nums[i] * (j + 1 < nums.length ? nums[j + 1] : 1));
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int max = Integer.MIN_VALUE;
        // For all elements in the range i to j, we choose all of them one by one
        // to make them the last balloon to be burst.
        //We choose the kth balloon as the last one to be burst. Now the subproblems will become independent since (k - 1)th balloon and (k + 1)th balloon won't need each other in order to calculate the answer.
        //The balloons in the range (i, k - 1) and (k + 1, j) will be burst BEFORE kth balloon. So, when we burst the kth balloon, the profit will be nums[i - 1] * nums[k] * nums[j + 1] PROVIDED that index i - 1 and j + 1 are valid. Because, i - 1, k and j + 1 will be come adjacent balloons
        for (int k = i; k <= j; k++) {
            int ans = maxCoinsH(nums, i, k - 1, dp) + maxCoinsH(nums, k + 1, j, dp) +
                    ((i - 1 >= 0 ? nums[i - 1] : 1) * nums[k] * (j + 1 < nums.length ? nums[j + 1] : 1));
            max = Math.max(max, ans);
        }
        return dp[i][j] = max;
    }
}
