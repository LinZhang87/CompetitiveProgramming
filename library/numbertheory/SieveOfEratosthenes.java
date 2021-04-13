package numbertheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author lzhang
 * @since 9/3/19
 */

/*
The Sieve of Eratosthenes is an algorithm used to generate all prime numbers smaller than N.
The method is to take increasingly larger prime numbers, and mark their multiples as composite.

For example, to find all primes less than 100, we would first mark [4, 6, 8, ...] (multiples of two),
then [6, 9, 12, ...] (multiples of three), and so on. Once we have done this for all primes less than N,
the unmarked numbers that remain will be prime.
 */

/*
Note that for any prime number p, the first useful multiple to check is actually p^2, not 2 * p.
This is because all numbers 2 * p, 3 * p, ..., i * p where i < p will already have been marked
when iterating over the multiples of 2, 3, ..., i respectively.

As a consequence of this we can make another optimization: since we only care about p^2 and above,
there is no need for x to range all the way up to N: we can stop at the square root of N instead.
 */


/*
Runtime: O(n(logn)(loglogn)),  Space: O(n)
 */
public class SieveOfEratosthenes {
    //get the count of prime number that is <= n
    public static int countOfPrime(int n) {
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);

        for(int p = 2; p * p <= n; p++) {
            if(prime[p]) {
                for(int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        int countOfPrime = 0;
        for(int i = 2; i <= n; i++) {
            if(prime[i]) {
                countOfPrime++;
            }
        }
        return countOfPrime;
    }

    public static boolean[] generatePrimeStatus(int n) {
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);

        for(int p = 2; p * p <= n; p++) {
            if(prime[p]) {
                for(int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        return prime;
    }

    /*
        Return the smallest prime factors for all numbers in [2, n]
     */
    public static int[] smallestPrimeFactor(int n) {
        boolean prime[] = new boolean[n + 1];
        int[] smallestPf = new int[n + 1];
        Arrays.fill(prime, true);
        for(int i = 0; i <= n; i++) {
            smallestPf[i] = i;
        }

        for(int p = 2; p * p <= n; p++) {
            if(prime[p]) {
                for(int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                    if(smallestPf[i] == i) {
                        smallestPf[i] = p;
                    }
                }
            }
        }
        return smallestPf;
    }

    //generate all prime numbers that is <= n, assuming no overflow
    public static List<Integer> generatePrime(int n) {
        List<Integer> res = new ArrayList<>();
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);

        for(int p = 2; p * p <= n; p++) {
            if(prime[p]) {
                for(int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        for(int i = 2; i <= n; i++) {
            if(prime[i]) {
                res.add(i);
            }
        }
        return res;
    }

    //generate all prime number indefinitely, without taking n as an input, assuming no overflow
    /*
    to generate primes without limit we need to rethink our data structure, as we can no longer store a boolean list to represent each number.
    Instead, we must keep track of the lowest unmarked multiple of each prime, so that when evaluating a new number we can check if it
    is such a multiple, and mark it as composite. This is a good candidate for a heap-based solution.
     */
    public static void generatePrimeIndefinitely() {
        PriorityQueue<int[]> minPq = new PriorityQueue<>((p1, p2) -> {return p1[0] - p2[0]; });
        int p = 2;

        while(true) {
            if(minPq.size() > 0 && p == minPq.peek()[0]) {
                //there will be keys that are multiples of more than 1 prime number. For example, 12 is a multiple of both 2 and 3.
                //So a while loop is needed here to update all priority keys
                while(p == minPq.peek()[0]) {
                    int[] entry = minPq.poll();
                    minPq.add(new int[]{entry[0] + entry[1], entry[1]});
                }
            }
            else {
                minPq.add(new int[]{p * p, p});
                System.out.println(p);
            }
            p++;
        }
    }

    public static void main(String[] args) {
        //generatePrimeIndefinitely();
        int[] spf = smallestPrimeFactor(20);
        System.out.println();
    }
}
