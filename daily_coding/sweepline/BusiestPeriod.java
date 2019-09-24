package sweepline;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author lzhang
 * @since 5/16/19
 */

/*
This problem was asked by Amazon.

You are given a list of data entries that represent entries and exits of groups of people into a building. An entry looks like this:

{"timestamp": 1526579928, count: 3, "type": "enter"}

This means 3 people entered the building. An exit looks like this:

{"timestamp": 1526580382, count: 2, "type": "exit"}

This means that 2 people exited the building. timestamp is in Unix time.

Find the busiest period in the building, that is, the time with the most people in the building.
Return it as a pair of (start, end) timestamps. You can assume the building always starts off and ends up empty, i.e. with 0 people inside.
 */

public class BusiestPeriod {
    class Event {
        int timestamp;
        int count;
        boolean enter;

        int getTimestamp() {
            return this.timestamp;
        }
    }
    public int[] getBusiestPeriod(List<Event> events) {
        if(events.size() < 2) {
            return new int[] {0, 0};
        }
        Collections.sort(events, Comparator.comparingInt(Event::getTimestamp));
        int start = events.get(0).getTimestamp(), end = 0, currPeopleInside = 0, maxPeopleInside = 0;

        int i = 1;
        while(i < events.size()) {
            while(i < events.size() && events.get(i).getTimestamp() == events.get(i - 1).getTimestamp()) {
                currPeopleInside += events.get(i).enter ? events.get(i).count : -1 * events.get(i).count;
            }

            if(currPeopleInside > maxPeopleInside) {
                start = events.get(i).getTimestamp();
                maxPeopleInside = currPeopleInside;
            }
            else if(currPeopleInside < maxPeopleInside){
                end = events.get(i).getTimestamp();
            }
        }
        return new int[] {start, end};
    }
}


//t1              t2              t3              t4
//enter 3         enter 1         enter 3         exit 4
//                exit 2          exit 1
//
//        3               2               4