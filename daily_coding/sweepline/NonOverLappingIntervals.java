package sweepline;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lzhang
 * @since 6/5/19
 */
/*
This problem was asked by Stripe.

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Intervals can "touch", such as [0, 1] and [1, 2], but they won't be considered overlapping.

For example, given the intervals (7, 9), (2, 4), (5, 8), return 1 as the last interval can be removed and the first two won't overlap.

The intervals are not necessarily sorted in any order.
 */

/*
Algorithm:
1. sort intervals by their start;
2. iterate through all intervals and check all adjacent intervals prev and curr. If prev and curr does not overlap, check the next pair.
If they overlap, these will be two cases:

(a) prev only intersects curr but does not contains curr completely: keep prev and remove curr;
(b) prev completely contains curr: keep curr and remove prev.

Alternatively,
1. sort intervals by their end;
2. when overlapping, keep the one that ends earlier and remove the other one.
 */

public class NonOverLappingIntervals {
    public static int minRemovalToNonOverLapping(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(Interval::getStart));
        int minRemoval = 0, i = 1;
        Interval prev = intervals[0];

        while(i < intervals.length) {
            Interval curr = intervals[i];
            if(curr.getStart() < prev.getEnd()) {
                if(curr.getEnd() <= prev.getEnd()) {
                    prev = curr;
                }
                minRemoval++;
            }
            else {
                prev = curr;
            }
            i++;
        }
        return minRemoval;
    }
    public static void main(String[] args) {
        Interval i1 = new Interval(0, 4);
        Interval i2 = new Interval(1, 3);
        Interval i3 = new Interval(2, 5);
        Interval i4 = new Interval(3, 8);
        Interval i5 = new Interval(9, 11);

        Interval[] intervals = {i1, i2, i3, i4, i5};
        System.out.println(minRemovalToNonOverLapping(intervals));
    }
}
