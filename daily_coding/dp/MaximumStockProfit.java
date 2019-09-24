package dp;

/**
 * @author lzhang
 * @since 6/7/19
 */
/*
This problem was asked by Affirm.

Given a array of numbers representing the stock prices of a company in chronological order,
write a function that calculates the maximum profit you could have made from buying and selling that stock.
You're also given a number fee that represents a transaction fee for each buy and sell transaction.

You must buy before you can sell the stock, but you can make as many transactions as you like.

For example, given [1, 3, 2, 8, 4, 10] and fee = 2, you should return 9, since you could buy the stock at 1 dollar,
and sell at 8 dollars, and then buy it at 4 dollars and sell it at 10 dollars. Since we did two transactions,
there is a 4 dollar fee, so we have 7 + 6 = 13 profit minus 4 dollars of fees.
 */

/*

dp[i]: the max profit that can be achieved by the ith day.

on the ith day, either we sell, or do nothing. Buy on the ith day is covered by a subproblem that sells at a later day.

so, dp[i] = Max of (dp[i - 1],  max of(prices[i] - prices[j] - fee + dp[j],  for all j < i))

 */

public class MaximumStockProfit {
    public static int maxStockProfit(int[] prices, int fee) {
        int[] dp = new int[prices.length];
        for(int i = 1; i < prices.length; i++) {
            dp[i] = dp[i - 1];
            for(int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], prices[i] - prices[j] - fee + dp[j]);
            }
        }

        int maxProfit = 0;
        for(int i = 0; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, dp[i]);
        }
        return maxProfit;
    }
    public static void main(String[] args) {
        int[] prices = {1,3,2,8,4,10};
        assert maxStockProfit(prices, 2) == 9;
    }
}
