package math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzhang
 * @since 6/24/19
 */

/*
This problem was asked by Apple.

A Collatz sequence in mathematics can be defined as follows. Starting with any positive integer:

if n is even, the next number in the sequence is n / 2
if n is odd, the next number in the sequence is 3n + 1
It is conjectured that every such sequence eventually reaches the number 1. Test this conjecture.

Bonus: What input n <= 1000000 gives the longest sequence?
 */


/*
Recursion with memoization:  limited by stack memory
 */

public class CollatzSequence {
    private static Map<Integer, Integer> map = new HashMap<>();
    private static int longestSequence = 0, optimalInput = 0;
    public static int longestSequence(int n) {
        map.put(1, 1);
        for(int i = 2; i <= n; i++) {
            longestSequenceHelper(i);
        }
        return optimalInput;
    }
    private static int longestSequenceHelper(int n ) {
        int seqLen = 0;
        if(map.containsKey(n)) {
            seqLen = map.get(n);
        }
        else {
            seqLen = 1 + (n % 2 == 0 ? longestSequenceHelper(n / 2) : longestSequenceHelper(n * 3 + 1));
        }
        if(seqLen > longestSequence) {
            longestSequence = seqLen;
            optimalInput = n;
        }
        map.put(n, seqLen);
        return seqLen;
    }
    public static void main(String[] args) {
        int bestInput = longestSequence(99999);
        System.out.println(bestInput);
        System.out.println(longestSequence);
    }
}
