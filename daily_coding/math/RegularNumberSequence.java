package math;

import java.util.*;

/**
 * @author lzhang
 * @since 9/5/19
 */

/*
A regular number in mathematics is defined as one which evenly divides some power of 60.
Equivalently, we can say that a regular number is one whose only prime divisors are 2, 3, and 5.

These numbers have had many applications, from helping ancient Babylonians keep time to tuning instruments according to the diatonic scale.

Given an integer N, write a program that returns, in order, the first N regular numbers.
 */

/*
Formally, a regular number is an integer of the form 2i·3j·5k, for nonnegative integers i, j, and k.
 */

/*
O(N * logN) runtime, O(N) space
 */
public class RegularNumberSequence {

    public static List<Integer> firstNRegularNumberTreeSet(int N) {
        List<Integer> seq = new ArrayList<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);

        while(N > 0) {
            int v = treeSet.pollFirst();
            seq.add(v);
            treeSet.add(v * 2);
            treeSet.add(v * 3);
            treeSet.add(v * 5);
            N--;
        }
        return seq;
    }

    public static List<Integer> firstNRegularNumberMinHeap(int N) {
        List<Integer> seq = new ArrayList<>();
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        minPq.add(1);
        int last = 0;
        while(N > 0) {
            int v = minPq.poll();
            if(v > last) {
                seq.add(v);
                last = v;
                minPq.add(v * 2);
                minPq.add(v * 3);
                minPq.add(v * 5);
                N--;
            }
        }
        return seq;
    }

    public static int[] firstNRegularNumberThreePointers(int N) {
        int[] seq = new int[N];
        seq[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for(int j = 1; j < N; j++) {
            seq[j] = Math.min(seq[i2] * 2, Math.min(seq[i3] * 3, seq[i5] * 5));
            if(seq[j] % 2 == 0) {
                i2++;
            }
            if(seq[j] % 3 == 0) {
                i3++;
            }
            if(seq[j] % 5 == 0) {
                i5++;
            }
        }
        return seq;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(firstNRegularNumberTreeSet(30).toArray(new Integer[0])));
        System.out.println(Arrays.toString(firstNRegularNumberMinHeap(30).toArray(new Integer[0])));
        System.out.println(Arrays.toString(firstNRegularNumberThreePointers(30)));
    }
}
