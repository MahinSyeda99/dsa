package two_dimensional_dp;

import java.util.Arrays;

public class BuyAndSellStockWithCooldown {

    //O(n^2) TC and O(n) SC
    public int maxProfit(int[] prices) {
        //0 => sell
        //1 => buy
        //dp[i][0] => max profit selling on the day or cooldown before selling
        //dp[i][1] => max profit buying on the day or cooldown before buying
        int[][] dp = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        //Initial call passing 0th index and day type as buy => 1
        return stockH(prices, dp, 0, 1);
    }

    public int stockH(int[] prices, int[][] dp, int i, int dayType) {
        if (i >= prices.length) {
            return 0;
        }

        if (dp[i][dayType] != -1) {
            return dp[i][dayType];
        }

        int profit = 0;
        if (dayType == 1) {
            //Buy on the day, so call for sell on the next day
            //Subtract current day's value from profit
            int option1 = -prices[i] + stockH(prices, dp, i + 1, 0);
            //Don't buy, cooldown
            int option2 = stockH(prices, dp, i + 1, 1);
            profit = Math.max(option1, option2);
        } else {
            //Sell on the day, so call for buy on the next to next day
            //Add current day's value to profit
            int option1 = prices[i] + stockH(prices, dp, i + 2, 1);
            //Don't sell, cooldown
            int option2 = stockH(prices, dp, i + 1, 0);
            profit = Math.max(option1, option2);
        }
        return dp[i][dayType] = profit;
    }

    public static void main(String[] args) {
        BuyAndSellStockWithCooldown buyAndSellStockWithCooldown = new BuyAndSellStockWithCooldown();
        int[] prices = {1, 2, 3, 0, 2};
        //o/p is 3
        System.out.println(buyAndSellStockWithCooldown.maxProfit(prices));
        prices = new int[]{1};
        //o/p is 0
        System.out.println(buyAndSellStockWithCooldown.maxProfit(prices));
    }
}
