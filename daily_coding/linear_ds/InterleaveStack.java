package linear_ds;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author lzhang
 * @since 5/25/19
 */

/*
This problem was asked by Google.

Given a stack of N elements, interleave the first half of the stack with the second half reversed using only one other queue. This should be done in-place.

Recall that you can only push or pop from a stack, and enqueue or dequeue from a queue.

For example, if the stack is [1, 2, 3, 4, 5], it should become [1, 5, 2, 4, 3]. If the stack is [1, 2, 3, 4], it should become [1, 4, 2, 3].

Hint: Try working backwards from the end state.
 */

public class InterleaveStack {
    public static void interleaveStack(Stack<Integer> stack) {
        Queue<Integer> q = new LinkedList<>();

        //empty stack and enqueue all its elements to queue
        while(stack.size() > 0) {
            q.add(stack.pop());
        }

        //push the first half of the queue to stack, to reverse their order
        int n = (q.size() + 1) / 2;
        for(int i = 0; i < n; i++) {
            stack.push(q.poll());
        }

        //interleave stack and queue back to queue to ensure a correct push order
        int qSize = q.size(), count = 0;
        //if stack has 1 more element that queue, enqueue the top of the stack
        if(n % 2 != 0) {
            q.add(stack.pop());
        }
        while(stack.size() > 0) {
            if (count < qSize) {
                q.add(q.poll());
                count++;
            }
            q.add(stack.pop());
        }

        //push all elements from queue back to stack
        while(q.size() > 0) {
            stack.push(q.poll());
        }
    }
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>();
        s1.push(5); s1.push(4); s1.push(3); s1.push(2); s1.push(1);
        interleaveStack(s1);

        while(s1.size() > 0) {
            System.out.print(s1.pop() +  " ");
        }

        Stack<Integer> s2 = new Stack<>();
        s2.push(4); s2.push(3); s2.push(2); s2.push(1);
        interleaveStack(s2);

        System.out.println();

        while(s2.size() > 0) {
            System.out.print(s2.pop() +  " ");
        }

        Stack<Integer> s3 = new Stack<>();
        s3.push(1);
        interleaveStack(s3);

        System.out.println();

        while(s3.size() > 0) {
            System.out.print(s3.pop() +  " ");
        }

        Stack<Integer> s4 = new Stack<>();
        s4.push(2); s4.push(1);
        interleaveStack(s4);

        System.out.println();

        while(s4.size() > 0) {
            System.out.print(s4.pop() +  " ");
        }
    }
}
