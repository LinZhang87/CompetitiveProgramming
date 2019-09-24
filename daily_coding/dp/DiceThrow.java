package dp;

/**
 * @author lzhang
 * @since 8/27/19
 */

/*
This problem was asked by Spotify.

Write a function, throw_dice(N, faces, total), that determines how many ways
it is possible to throw N dice with some number of faces each to get a specific total.

For example, throw_dice(3, 6, 7) should equal 15.
 */

/*
Dynamic programming:
dp[i][j]: the number of ways to get total j with i dices;
 */
public class DiceThrow {

    public static int throwDice(int N, int faces, int total) {
        int[][] dp = new int[N][total + 1];
        for(int j = 1; j <= faces && j <= total; j++) {
            dp[0][j] = 1;
        }
        for(int i = 1; i < N; i++) {
            for(int j = 1; j <= total; j++) {
                for(int k = 1; k <= faces; k++) {
                    if(j > k) {
                        dp[i][j] += dp[i - 1][j - k];
                    }
                }
            }
        }
        return dp[N - 1][total];
    }
    public static void main(String[] args) {
        System.out.println(throwDice(1,6,3));
        System.out.println(throwDice(3,6,40));
    }
}
