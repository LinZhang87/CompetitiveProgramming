package dp;

/**
 * @author lzhang
 * @since 7/6/19
 */

/*
This problem was asked by Square.

In front of you is a row of N coins, with values v1, v2, ..., vn.

You are asked to play the following game. You and an opponent take turns choosing either the first or last coin from the row,
removing it from the row, and receiving the value of the coin.

Write a program that returns the maximum amount of money you can win with certainty, if you move first, assuming your opponent plays optimally.
 */
public class CoinGame {
    public static int maxValue(int[] coins) {
        int[] prefixSum = new int[coins.length + 1];
        for(int i = 1; i <= coins.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + coins[i - 1];
        }
        int[][] dp = new int[coins.length][coins.length];
        for(int i = 0; i < coins.length; i++) {
            dp[i][i] = coins[i];
        }
        for(int i = 0; i < coins.length - 1; i++) {
            dp[i][i + 1] = Math.max(coins[i], coins[i + 1]);
        }
        for(int coinNum = 3; coinNum <= coins.length; coinNum++) {
            for(int startCoin = 0; startCoin <= coins.length - coinNum; startCoin++) {
                int endCoin = startCoin + coinNum - 1;
                dp[startCoin][endCoin] = Math.max(coins[startCoin] + prefixSum[endCoin + 1] - prefixSum[startCoin + 1] - dp[startCoin + 1][endCoin],
                                                        coins[endCoin] + prefixSum[endCoin] - prefixSum[startCoin] - dp[startCoin][endCoin - 1]);
            }
        }
        return dp[0][coins.length - 1];
    }
    public static void main(String[] args) {
        int[] coins = {10, 24, 5, 9};
        System.out.println(maxValue(coins));
    }
}
