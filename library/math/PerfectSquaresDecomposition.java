package math;

/**
 * @author lzhang
 * @since 11/14/19
 */

/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 */

/*
this problem can be solved more efficiently with math, though likely not in an interview setting.
Two theorems will be useful here, both due to the 18th-century mathematician Lagrange.
The first says that any integer can be expressed as the sum of at most four perfect squares.
And second, an integer is the sum of three perfect squares if and only if it cannot be expressed in the form N = 4^a * (8 * b + 7), where a and b are integers.

For example, 15 can be expressed as 4^0 * (8 * 1 + 7), which implies that, try as hard as we might, there is no way to express 15 as the sum of three perfect squares.

This leads us to the following case-based algorithm:

If the number itself is a perfect square, return 1.
If the number is the sum of two perfect squares, return 2.
If the number cannot be expressed in the form above, return 3.
Else, return 4.
 */

public class PerfectSquaresDecomposition {
    //O(N ^ 1.5) Dp
    public int leastNumberPerfectSquaresDp(int n) {
         int[] dp = new int[n + 1];
         for(int i = 0; i <= n; i++) {
             dp[i] = i;
         }
         dp[0] = 0;
         for(int i = 1; i <= n; i++) {
             for(int j = 1; j * j <= i; j++) {
                 dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
             }
         }
         return dp[n];
     }

     //O(N ^ 0.5) Math
    public int leastNumberPerfectSquaresMath(int n) {
        if(isSquare(n)) {
            return 1;
        }
        for(int i = 1; i * i <= n; i++) {
            if(isSquare(n - i * i)) {
                return 2;
            }
        }
        while(n % 4 == 0) {
            n /= 4;
        }
        if(n % 8 == 7) {
            return 4;
        }
        return 3;
    }

    private boolean isSquare(int n) {
        int sq = (int)Math.sqrt(n);
        return sq * sq == n;
    }
}
