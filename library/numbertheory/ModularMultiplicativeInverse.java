package numbertheory;

/**
 * @author lzhang
 * @since 3/9/20
 */

/*
    A modular multiplicative inverse of an integer a is an integer x such that a⋅x is congruent to 1 modular some modulus m.
    To write it in a formal way: we want to find an integer x so that a⋅x ≡ 1 mod m.

    It can be proven that the modular inverse exists if and only if a and m are coprime(gcd(a, m) == 1).
 */
public class ModularMultiplicativeInverse {
    /*
        Finding the modular inverse using Extended Euclidean Algorithm
     */
    public static long modInv_EEA(long a, long mod) {
         long[] res = Euclid.solveGcdEquation(a, mod);
         if(res[2] != 1) {
             // a and m are not coprime, modular inverse of a by m does not exist.
             return -1;
         }
         return (res[0] + mod) % mod;
    }

    /*
        Finding the modular inverse of every number module mod in O(mod).
        Compute the modular inverse for every number in the range[1, mod - 1].
        Pre-condition: mod is a prime number

        Usually in contests, we are only required to compute for range [1, O(10^5)].
     */
    public static long[] modularInverseForRangeLinear(int mod) {
        long[] inv = new long[mod];
        inv[1] = 1;
        for(int i = 2; i < mod; i++) {
            inv[i] = mod - inv[mod % i] * (mod / i) % mod;
        }
        return inv;
    }

    /*
        If mod is a prime number, we can also use the binary exponentiation algorithm to compute all numbers's
        modular inverse in [1, mod - 1] in O(mod * log(mod)) time.
     */
    public static long[] modularInverseForRangeBinaryExponentiation(int mod) {
        long[] inv = new long[mod];

        return inv;
    }
}
