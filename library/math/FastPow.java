package math;

import java.math.BigInteger;

/**
 * @author lzhang
 * @since 10/17/19
 */

/*
    O(logN) time to compute pow(x, n) and pow(x, n, mod);
    pow(x, n, mod) = pow(x, n) % mod
 */

public class FastPow {
    /*
        Java Big Integer
        BigInteger operations return a new BigInteger object
     */
    public long fastPow1(int x, int n) {
        BigInteger res = BigInteger.valueOf(x);
        res = res.pow(n);
        return res.longValue();
    }

    public int fastModPow1(int x, int n, int mod) {
        BigInteger res = BigInteger.valueOf(x);
        res = res.modPow(BigInteger.valueOf(n), BigInteger.valueOf(mod));
        return res.intValue();
    }

    /*
        Bottom up binary
     */
    public long fastPow2(int x, int n) {
        if(n == 0) {
            return 1;
        }
        long coeff = 1, base = x;
        while(n > 1) {
            if(n % 2 != 0) {
                coeff *= base;
            }
            base *= base;
            n = n / 2;
        }
        long res = coeff * base;
        return res;
    }

    public int fastModPow2(int x, int n, int mod) {
        if(n == 0) {
            return 1;
        }
        long coeff = 1, base = x;
        while(n > 1) {
            if(n % 2 != 0) {
                coeff = (coeff * base % mod);
            }
            base = (base * base % mod);
            n = n / 2;
        }
        long res = coeff * base % mod;
        return (int)res;
    }

    /*
        Top down recursive binary
     */
    public long fastPow3(int x, int n) {
        if(n == 0) {
            return 1;
        }
        long half = fastPow3(x, n / 2);
        long res = half * half;
        if(n % 2 == 0) {
            return res;
        }
        return res * x;
    }

    public int fastModPow3(int x, int n, int mod) {
        if(n == 0) {
            return 1;
        }
        long half = fastModPow3(x, n / 2, mod);
        long res = half * half;
        if(n % 2 == 0) {
            return (int)res;
        }
        return (int)(res * x % mod);
    }
}
