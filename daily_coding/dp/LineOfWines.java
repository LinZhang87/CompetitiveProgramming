package dp;

/**
 * @author lzhang
 * @since 9/14/19
 */

/*
There are N wines in a row. Each year you sell either the leftmost or the rightmost wine.
The i-th wine has initial price p[i] and price k * p[i] in the k-th year. What is the maximum possible total profit?
 */

public class LineOfWines {
    /*
    state:
    dp[i][j]: the max possible profit of wines[i, j] at year i - 0 + N - 1 - j + 1 = N + i - j
    dp[i][j] = max of {p[i] * (N + i - j) + dp[i + 1][j], p[j] * (N + i -j) + dp[i][j - 1]}

    Initialization:
    dp[i][i] = N * p[i]

    Ans:
    dp[0][N - 1]
     */
    public static int maxProfitBottomUp(int[] p) {
        int N = p.length;
        int[][] dp = new int[N][N];
        for(int i = 0; i < N; i++) {
            dp[i][i] = N * p[i];
        }
//        for(int i = N - 1; i >= 0; i--) {
//            for(int j = i + 1; j < N; j++) {
//                dp[i][j] = Math.max(p[i] * (N + i - j) + dp[i + 1][j], p[j] * (N + i -j) + dp[i][j - 1]);
//            }
//        }
        for(int len = 2; len <= N; len++) {
            for(int l = 0; l <= N - len; l++) {
                int r = l + len - 1;
                int y = N + l - r;
                dp[l][r] = Math.max(p[l] * y + dp[l + 1][r], p[r] * y + dp[l][r - 1]);
            }
        }
        return dp[0][N - 1];
    }
    public static void main(String[] args) {
        int[] w = {2,4,6,2,5};
        System.out.println(maxProfitBottomUp(w));
    }
}
