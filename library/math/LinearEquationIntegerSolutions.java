package math;

/**
 * @author lzhang
 * @since 12/4/19
 */

/*
    Given a linear equation of n variables, find the total number of non-negative integer solutions of it. All coefficients are positive.

    Example:

    input: 1 * x + 2 * y = 5

    output: 3,  the 3 possible integer solutions are x = 1, y = 2; x = 3, y = 1; x = 5, y = 0.


    This problem can be simply thought as a coin change problem.
    You are given coins of different denominations and a total amount of money.
    Write a function to compute the number of combinations that make up that amount.
    You may assume that you have infinite number of each kind of coin.

    rhs is the sum.
    coeffients are the coin denominations.
    each variable is the number of one coin denomination.
 */

public class LinearEquationIntegerSolutions {
    public static int countSolutions(int[] coeff, int n, int rhs) {
        int[] dp = new int[rhs + 1];
        dp[0] = 1;  // if rhs == 0, there is only one solution: all variables are 0.

        for(int i = 0; i < n; i++) {
            for(int j = coeff[i]; j <= rhs; j++) {
                dp[j] += dp[j - coeff[i]];
            }
        }
        return dp[rhs];
    }

    public static void main(String[] args) {
        int[] coeff = {1,2};
        countSolutions(coeff, 2, 5);
    }
}
