package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzhang
 * @since 7/28/19
 */

/*
The Sieve of Eratosthenes is an algorithm used to generate all prime numbers smaller than N.
The method is to take increasingly larger prime numbers, and mark their multiples as composite.

For example, to find all primes less than 100, we would first mark [4, 6, 8, ...] (multiples of two), then [6, 9, 12, ...] (multiples of three), and so on.
Once we have done this for all primes less than N, the unmarked numbers that remain will be prime.

Implement this algorithm.

Bonus: Create a generator that produces primes indefinitely (that is, without taking N as an input).
 */

/*
Create a genarator that produces primes indefinitely.  Re-use the sieve of eratosthenes algorithm and provide an
ongoing internal intervals, say, 100 then do the following.

1. produces all primes in order that are smaller than 100;
2. reinit the marked array to all false, representing the range of [100, 200).
   find the 1st prime number that is prime, repeat sieve algorithm.
3. Each time we terminate on a range, advance to the next interval with a new initialized marked array
 */

public class SieveOfEratosthenes {
    public static List<Integer> primeSmallerThanTarget(int n) {
        List<Integer> primes = new ArrayList<>();
        if(n > 2) {
            boolean[] marked = new boolean[n + 1];
            int p = 2;

            while(true) {
                while(p < n && marked[p]) {
                    p++;
                }
                if(p == n) {
                    break;
                }
                int curr = p * 2;
                while(curr < n) {
                    marked[curr] = true;
                    curr += p;
                }
                p++;
            }
            for(int i = 2; i < n; i++) {
                if(!marked[i]) {
                    primes.add(i);
                }
            }
        }
        return primes;
    }
    public static void main(String[] args) {
        List<Integer> res = primeSmallerThanTarget(121);
        System.out.println(Arrays.toString(res.toArray(new Integer[0])));
    }
}

