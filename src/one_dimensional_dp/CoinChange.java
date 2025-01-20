package one_dimensional_dp;

import java.util.Arrays;

public class CoinChange {

    //O(n * amount) TC and O(amount) SC
    public int coinChange(int[] coins, int amount) {
        //dp to store the minimum coins needed to form the amounts from 0 to amount + 1
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        //Base case
        dp[0] = 0; //No. of coins to get amount 0 is 0
        //Bottom up DP approach
        //Iterate through each amount
        for (int i = 1; i < amount + 1; i++) {
            //Iterate through each coin, and find the minimum coins we need to get the current amount
            for (int j = 0; j < coins.length; j++) {
                //We only want coins which are less than or equal to amount i and also only if there is a way to get amount i - coin value
                if (i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    //We include the current coin and the ways for amount i - current coin
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }
        //We will return final dp value for amount only if its not default value set, otherwise return -1 indicating there is no solution
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {1, 2, 5};
        //o/p is 3
        System.out.println(coinChange.coinChange(coins, 11));
        coins = new int[]{2};
        //o/p is -1
        System.out.println(coinChange.coinChange(coins, 3));
        coins = new int[]{1};
        //o/p is 0
        System.out.println(coinChange.coinChange(coins, 0));
        coins = new int[]{186, 419, 83, 408};
        //o/p is 20
        System.out.println(coinChange.coinChange(coins, 6249));
    }
}
