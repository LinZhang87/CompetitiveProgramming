package numbertheory;

import java.util.List;

/**
 * @author lzhang
 * @since 9/21/19
 */

public class Euclid {

    //gcd between two integers a and b, O(log n) runntime time, n == min(a, b)
    public static int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    //gcd among all the integers in array nums
    public static int gcd(int[] nums) {
        if(nums.length < 2) {
            return 0;
        }
        int gcd = gcd(nums[1], nums[0]);
        for(int i = 2; i < nums.length; i++) {
            gcd = gcd(nums[i], gcd);
        }
        return gcd;
    }

    //lcm: lowest common multiple
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }


    /*
        Find the LCM of all numbers from a to b.
        The answer will exceed integer limit very quickly. For example, for long type, if a = 1, b = 43,
        the LCM of [1, 43] already overflows.

        Reference on LCM sequence for [1, n]: http://oeis.org/A003418/b003418.txt

        This method works well if we only have one input.
     */
    public static int lcmForRangeSimple(int a, int b) {
        int ans = a;
        for(int i = a + 1; i <= b; i++) {
            ans = ans * i / gcd(ans, i);
        }
        return ans;
    }

    /*
        Given an array of integers a[i], all a[i] <= 10^6; Find the LCM of the first a[i] natural numbers.
        Remember, this implementation is just to demonstrate this algorithm, more attention is needed to
        take care of overflow.

        When we need to handle many inputs to compute LCM for [1, a[i]], it is better that we use Sieve of
        Eratosthenes to store all prime factors.

        LCM(x, y) = z. Any prime factor of x or y will also be a prime factor of z.

        1. init lcm = 1;
        2. generate all prime numbers <= upper bound;
        3. for each prime p that is <= a[i], multiply itself until right before the product is > a[i].
        4. multiply the result from step 3; repeat step 3 on the next prime number.

        Reference: https://www.geeksforgeeks.org/lcm-first-n-natural-numbers/
     */
    public static int[] lcmForRangeSieve(int[] a) {
        List<Integer> primes = SieveOfEratosthenes.generatePrime((int)1e6);
        int[] ans = new int[a.length];
        for(int i = 0; i < a.length; i++) {
            int lcm = 1;
            for(int j = 0; j < primes.size() && primes.get(j) <= a[i]; j++) {
                int curr = primes.get(j);
                while(curr * primes.get(j) <= a[i]) {
                    curr = curr * primes.get(j);
                }
                lcm *= curr;
            }
            ans[i] = lcm;
        }
        return ans;
    }

    //Extend Euclid to solve a * x + b * y = gcd(a, b)
    //Proof:
    //gcd(a, b) == gcd(b, a % b), so first solve b * x' + a % b * y' = gcd(b, a % b) = gcd(a, b).
    //Using a % b == a - (a / b) * b, reorganize the left side of equation to:
    //b * x' + (a - (a / b) * b) * y' = gcd(a, b)
    //a * (y') + b * (x' - (a / b) * y') = gcd(a, b)
    //x = y';   y = x' - (a / b) * y';

    /*
        return: long[0]: x; long[1]: y; long[2]: gcd(a, b)
     */
    public static long[] solveGcdEquation(long a, long b) {
        if(b == 0) {
            return new long[]{1, 0, a};
        }
        long[] res = solveGcdEquation(b, a % b);
        return new long[]{res[1], res[0] - (a / b) * res[1], res[2]};
    }


    public static void main(String[] args) {
        int a = lcmForRangeSimple(1, 10);
        int b = lcmForRangeSimple(1, 7);
        int c = lcmForRangeSimple(8, 10);
        int[] arr = {10};
        int[] ans = lcmForRangeSieve(arr);
        System.out.println(b * c == a);
    }
}
