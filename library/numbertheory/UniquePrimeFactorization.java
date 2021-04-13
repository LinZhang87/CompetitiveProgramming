package numbertheory;

import java.util.*;

/**
 * @author lzhang
 * @since 12/18/19
 */

/*
    For every number that is > 1 and is not prime, there is a unique prime factorization
 */
public class UniquePrimeFactorization {
    /*
        Given an integer n, return its prime factorization
     */
    public static List<Integer> uniquePrimeFactorization(int n) {
        List<Integer> factor = new ArrayList<>();

        //If n is a composite number then there is at least 1 prime divisor of n <= Math.sqrt(n)

        //x is defined as int, so x * x may overflow, causing TLE
        //either define x as long, or replace x * x <= n with x <= Math.sqrt(n) to avoid overflow
        for(long x = 2; x * x <= n; x++) {
            while(n % x == 0) {
                factor.add((int)x);
                n /= x;
            }
        }
        if(n > 1) {
            factor.add(n);
        }
        return factor;
    }

    /*
        Given an upper bound integer n, return prime factorizations of all numbers in [2, n].

        A prime number does not have such an factorization, an empty list should be returned in this case.

        This is an application based on the sieve of eratosthenes algorithm.

        Each number N has O(log N) prime factors. So the running time is O(n * log log n) + O(n * log n). This
        approach is better than calling uniquePrimeFactorization on each number in that it keeps smaller numbers'
        smallest prime factor, so we do not restart the entire factorization from scratch for each different number.

        Alternatively, we can save the entire prime factorization of smaller numbers, then use them when computing bigger
        numbers that are multiples of these smaller numbers.
     */

    public static List<Integer>[] primeFactorizationForAllNumbers(int n) {
        int[] smallestPrimeFactor = SieveOfEratosthenes.smallestPrimeFactor(n);
        List<Integer>[] factors = new List[n + 1];
        for(int i = 0; i <= n; i++) {
            factors[i] = new ArrayList<>();
        }
        for(int i = 2; i <= n; i++) {
            if(smallestPrimeFactor[i] != i) {
                int j = i;
                while(smallestPrimeFactor[j] != j) {
                    factors[i].add(smallestPrimeFactor[j]);
                    j /= smallestPrimeFactor[j];
                }
                factors[i].add(j);
            }
        }
        return factors;
    }

    public static void main(String[] args) {
        List<Integer>[] ans = primeFactorizationForAllNumbers(12);
        System.out.println();
//        List<Integer> ans = uniquePrimeFactorization(17);
//        System.out.println(ans.toString());
    }
}
