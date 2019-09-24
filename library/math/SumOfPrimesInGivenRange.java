package math;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 9/9/19
 */

/*
Given a range [L, R]. Find the sum of all the prime numbers in the given range from L to R both inclusive.
 */
public class SumOfPrimesInGivenRange {
    //O(N) time to compute the prefix sum as a preprocessing step.
    //prefixSum[i]: the sum of all prime numbers from 0 to i(inclusive)
    private static long[] calculatePrefixSum(int r) {
        boolean[] prime = new boolean[r + 1];
        long[] prefixSum = new long[r + 1];
        Arrays.fill(prime, true);

        for(int p = 2; p * p <= r; p++) {
            if(prime[p]) {
                for(int i = p * p; i <= r; i += p) {
                    prime[i] = false;
                }
            }
        }
        for(int i = 2; i <= r; i++) {
            prefixSum[i] = prefixSum[i - 1] + (prime[i] ? i : 0);
        }
        return prefixSum;
    }
    //O(1) query time for a given range
    public static long sumOfPrimesInGivenRange(int l, int r) {
        long[] prefixSum = calculatePrefixSum(r);
        return prefixSum[r] - prefixSum[l - 1];
    }

    public static void main(String[] args) {
        System.out.println(sumOfPrimesInGivenRange(15, 25));
    }
}
