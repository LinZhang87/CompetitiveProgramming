package combinatorics;

import numbertheory.ModularMultiplicativeInverse;

/**
 * @author lzhang
 * @since 3/9/20
 */

public class BinomialCoefficent {
    public static long[] factorial(int n, long mod) {
        long[] factor = new long[n + 1];
        factor[0] = 1;
        for(int i = 1; i <= n; i++) {
            factor[i] = factor[i - 1] * i % mod;
        }
        return factor;
    }

    public static long binomialCoefficent(long[] factor, long n, long k, long mod) {
        return factor[(int)n] * ModularMultiplicativeInverse.modInv_EEA(factor[(int)k], mod) % mod
                * ModularMultiplicativeInverse.modInv_EEA(factor[(int)(n - k)], mod) % mod;
    }
}
