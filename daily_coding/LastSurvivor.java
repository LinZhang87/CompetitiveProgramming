/**
 * @author lzhang
 * @since 7/9/19
 */

/*
This problem was asked by Bloomberg.

There are N prisoners standing in a circle, waiting to be executed.
The executions are carried out starting with the kth person, and removing every successive kth person going clockwise until there is no one left.

Given N and k, write an algorithm to determine where a prisoner should stand in order to be the last survivor.

For example, if N = 5 and k = 2, the order of executions would be [2, 4, 1, 5, 3], so you should return 3.

Bonus: Find an O(log N) solution if k = 2.
 */

/*
A naive solution is to do exactly what is described in the statement. create a circular doubly linked list to simulate the initial circle, each node
represents a person.
The runtime is O(N * k).

Can you do better? O(N) for a general k?
 */
public class LastSurvivor {
    static class LinkedListNode {
        int val;
        LinkedListNode prev;
        LinkedListNode next;

        LinkedListNode(int val) {
            this.val = val;
        }
    }
    public static int positionToSurviveLast(int N, int k) {
        LinkedListNode[] p = new LinkedListNode[N];
        int remain = N;
        for(int i = 0; i < N; i++) {
            p[i] = new LinkedListNode(i + 1);
        }
        for(int i = 0; i < N; i++) {
            p[i].prev = p[(i - 1 + N) % N];
            p[i].next = p[(i + 1) % N];
        }
        LinkedListNode start = p[(k - 1) % N];
        while(remain > 1) {
            LinkedListNode newStart = start.prev;
            start.next.prev = newStart;
            newStart.next = start.next;
            start.prev = null;
            start.next = null;
            remain--;

            start = newStart;
            int c = k;
            while(c > 0) {
                start = start.next;
                c--;
            }
        }
        return start.val;
    }
    public static int positionToSurviveLastLog(int N) {
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(positionToSurviveLast(5, 2));    //ans: 3
        System.out.println(positionToSurviveLast(5, 3));    //ans: 4
        System.out.println(positionToSurviveLast(5, 6));    //ans: 4
        System.out.println(positionToSurviveLast(6, 2));    //ans: 5
    }
}
