package dp;

/**
 * @author lzhang
 * @since 10/24/19
 */

/*
Consider a multiset of integers S, the union of n closed intervals of positive integers: S = [l1..r1] ∪ [l2..r2] ∪ · · · ∪ [ln..rn]
(recall that a closed interval [l..r] contains integers {l, l + 1, . . . , r}).
Let D be the set of unique integers in S. For each x in D, find the number of occurrences of x in S.

Input:
The first line contains an integer n (1 ≤ n ≤ 100 000), the number of intervals in the union. Each of the next n lines contains
two integers li and ri (1 ≤ li ≤ ri ≤ 100 000), the left and right boundaries of the i-th interval.

Output:
For each integer x in D, print two integers on a separate line: x and its number of occurrences in S.
 */

public class IntervalFrequency {
    //Find the frequencies of all unique numbers covered by all intervals in O(N) time,
    //where N is Math.max(interval total number, total possible unique integers)
    //Assume all integers covered by all intervals  are in range [1, N]

    public int[] getFrequency(int[][] intervals, int N) {
        int[] freq = new int[N + 2];

        for(int i = 0; i < intervals.length; i++) {
            freq[intervals[i][0]]++;
            freq[intervals[i][1] + 1]--;
        }

        for(int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }

        return freq;
    }
}
