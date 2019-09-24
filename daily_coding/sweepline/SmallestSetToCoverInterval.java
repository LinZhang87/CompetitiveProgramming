package sweepline;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lzhang
 * @since 3/29/19
 */

/*
Given a set of closed intervals, find the smallest set of numbers that covers all the intervals. If there are multiple smallest sets, return any of them.

For example, given the intervals [0, 3], [2, 6], [3, 4], [6, 9], one set of numbers that covers all these intervals is {3, 6}.
 */
public class SmallestSetToCoverInterval {
    public Set<Integer> smallestSetToCoverInterval(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(Interval::getStart));
        Set<Integer> set = new HashSet<>();

        int i = 0;
        while (i < intervals.length) {
            Interval interval = intervals[i];

            while (i < intervals.length && intersect(interval, intervals[i])) {
                interval.setStart(Math.max(interval.getStart(), intervals[i].getStart()));
                interval.setEnd(Math.min(interval.getEnd(), intervals[i].getEnd()));
                i++;
            }

            set.add(interval.getEnd());
        }
        return set;
    }

    private boolean intersect(Interval i1, Interval i2){
        return !(i1.getStart() > i2.getEnd() || i2.getStart() > i1.getEnd());
    }
}
