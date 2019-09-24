package datastructures;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author lzhang
 * @since 8/29/19
 */

/*
Let's represent an integer in a linked list format by having each node represent a digit in the number. The nodes make up the number in reversed order.

For example, the following linked list:

1 -> 2 -> 3 -> 4 -> 5
is the number 54321.

Given two linked lists in this format, return their sum in the same linked list format.

For example, given

9 -> 9
5 -> 2
return 124 (99 + 25) as:

4 -> 2 -> 1
 */
public class LinkedListSum {
    public static LinkedList<Integer> sum(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        ListIterator<Integer> iter1 = l1.listIterator(0);
        ListIterator<Integer> iter2 = l2.listIterator(0);
        LinkedList<Integer> sum = new LinkedList<>();
        int s = 0, c = 0;
        while(iter1.hasNext() || iter2.hasNext()) {
            s = (iter1.hasNext() ? iter1.next() : 0) + (iter2.hasNext() ? iter2.next() : 0) + c;
            sum.add(s % 10);
            c = s / 10;
        }
        if(c > 0) {
            sum.add(c);
        }
        return sum;
    }
}
