package datastructures;

import java.util.Random;
import java.util.Stack;

/**
 * @author lzhang
 * @since 6/13/19
 */

/*
This problem was asked by Facebook.

Given a string of parentheses, find the balanced string that can be produced from it using the minimum number of
insertions and deletions. If there are multiple solutions, return any of them.

For example, given "(()", you could return "(())". Given "))()(", you could return "()()()()".
 */

public class MinOpsToBalancedString {

    public static String balancedStringUsingMinOps(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == ')' && (!stack.isEmpty() && chars[stack.peek()] == '(')) {
                stack.pop();
            }
            else {
                stack.push(i);
            }
        }

        while(!stack.isEmpty()) {
            chars[stack.pop()] = '#';
        }
        StringBuilder sb = new StringBuilder();
        for(char c : chars) {
            if(c != '#') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Random rand1 = new Random();
        Random rand2 = new Random();
        char[] chars = {'(', ')'};

        for(int i = 0; i < 10; i++) {
            int n = 10 + rand1.nextInt(5);
            char[] seq = new char[n];
            for(int j = 0; j < n; j++) {
                seq[j] = chars[rand2.nextInt(2)];
            }
            String s = new String(seq);
            System.out.print(s);
            System.out.print("          ");
            System.out.print(balancedStringUsingMinOps(s));
            System.out.println();
        }
    }
}

