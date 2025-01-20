package slidingWindow;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {

    //O(n) TC
    public int maxProfit(int[] prices) {
        int maxVal = prices[prices.length - 1];
        int max = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            maxVal = Math.max(maxVal, prices[i]);
            if (maxVal - prices[i] > max) {
                max = maxVal - prices[i];
            }
        }
        return max;
    }
}
