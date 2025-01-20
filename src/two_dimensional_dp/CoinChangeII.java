package two_dimensional_dp;

import java.util.Arrays;

public class CoinChangeII {

    //O(n*amount) TC and O(amount) SC
    public int changeUsing1DDP(int amount, int[] coins) {
        //dp[i] represents No.of ways of making amount i from the coins
        int[] dp = new int[amount + 1];

        //Base case
        dp[0] = 1; //No of ways of to make up amount 0 is 1(by not including anything)

        //For each coin, we find the no. ways of making up amount from coin value to amount
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coin];
            }
        }
        return dp[amount];
    }

    //O(n * amount) TC solution with O(n * amount) SC
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i < coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return changeH(amount, coins, 0, dp);
    }

    public int changeH(int amount, int[] coins, int i, int[][] dp) {
        if (amount == 0) {
            return 1; // One way to make the amount with current coins
        }

        if (i >= coins.length || amount < 0) {
            return 0; // No way to make the amount with current coins
        }

        if (dp[i][amount] != -1) {
            return dp[i][amount];
        }

        //Include
        int include = changeH(amount - coins[i], coins, i, dp);
        //Exclude
        int exclude = changeH(amount, coins, i + 1, dp);
        return dp[i][amount] = include + exclude;
    }

    public static void main(String[] args) {
        CoinChangeII coinChangeII = new CoinChangeII();
        int[] coins = {1, 2, 5};
        System.out.println(coinChangeII.change(5, coins));
    }
}
