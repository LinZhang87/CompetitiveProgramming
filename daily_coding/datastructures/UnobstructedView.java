package datastructures;

import java.util.Stack;

/**
 * @author lzhang
 * @since 9/7/19
 */

/*
You are given an array representing the heights of neighboring buildings on a city street, from east to west.
The city assessor would like you to write an algorithm that returns how many of these buildings
have a view of the setting sun, in order to properly value the street.

For example, given the array [3, 7, 8, 3, 6, 1], you should return 3, since the top floors of the buildings
with heights 8, 6, and 1 all have an unobstructed view to the west.

Can you do this using just one forward pass through the array?
 */

public class UnobstructedView {
    public static int backwardPass(int[] a) {
        int count = 0, currMax = 0;
        for(int i = a.length - 1; i >= 0; i--) {
            if(a[i] > currMax) {
                count++;
                currMax = a[i];
            }
        }
        return count;
    }

    public static int forwardPass(int[] a) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < a.length; i++) {
            while(stack.size() > 0 && a[stack.peek()] <= a[i]) {
                stack.pop();
            }
            stack.push(i);
        }
        return stack.size();
    }
    public static void main(String[] args) {
        int[] a = {3,7,8,3,6,1};
        System.out.println(backwardPass(a));
        System.out.println(forwardPass(a));
    }
}
