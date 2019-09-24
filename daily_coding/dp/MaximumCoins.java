package dp;

/**
 * @author lzhang
 * @since 3/28/19
 */

/*
Daily Coding Problem 122

You are given a 2-d matrix where each cell represents number of coins in that cell. Assuming we start at matrix[0][0],
and can only move right or down, find the maximum number of coins you can collect by the bottom right corner.

For example, in this matrix

0 3 1 1
2 0 0 4
1 5 3 1
The most we can collect is 0 + 2 + 1 + 5 + 3 + 1 = 12 coins.
 */

public class MaximumCoins {
    public static int maximumCoins(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for(int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    public static int maximumCoinsWithSpaceOptimization(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[2][m];
        dp[0][0] = matrix[0][0];
        for(int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        for(int i = 1; i < n; i++) {
            dp[i % 2][0] = dp[(i - 1) % 2][0] + matrix[i][0];
            for(int j = 1; j < m; j++) {
                dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]) + matrix[i][j];
            }
        }
        return dp[(n - 1) % 2][m - 1];
    }
    public static void main(String[] args) {
        int[][] matrix = {{0,3,1,1},{2,0,0,4},{1,5,3,1}};
        System.out.println(maximumCoinsWithSpaceOptimization(matrix));
    }
}
