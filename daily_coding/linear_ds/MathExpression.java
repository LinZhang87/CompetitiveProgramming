package linear_ds;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author lzhang
 * @since 8/27/19
 */

/*
Given a string consisting of parentheses, single digits, and positive and negative signs,
convert the string into a mathematical expression to obtain the answer.

Don't use eval or a similar built-in parser.

For example, given '-1 + (2 + 3)', you should return 4.
 */

/*
Algorithm 1: recursion

Algorithm 2: iterative with parentheses depth pre-processing
 */
public class MathExpression {
    public static int evaluate(String s) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> parentheseMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            }
            else if(s.charAt(i) == ')') {
                parentheseMap.put(stack.pop(), i);
            }
        }
        return evalRecursion(parentheseMap, s, 0, s.length() - 1);
    }
    private static int evalRecursion(Map<Integer, Integer> map, String s, int start, int end) {
        int sum = 0, sign = 1, num = 0;
        for(int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if(!Character.isDigit(c)) {
                if(c == '+' || c == '-') {
                    sum += sign * num;
                    sign = c == '+' ? 1 : -1;
                    num = 0;
                }
                else {
                    num = evalRecursion(map, s, i + 1, map.get(i) - 1);
                    i = map.get(i);
                }
            }
            else {
                num = num * 10 + (c - '0');
            }
        }
        sum += sign * num;
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(evaluate("-1+(2+3)"));     //4
        System.out.println(evaluate("-1+(-2+3)"));    //0
        System.out.println(evaluate("-1-2-3"));       //-6
        System.out.println(evaluate("1-(2+3)"));      //-4
        System.out.println(evaluate("1-(2-(3+4))"));  //6
        System.out.println(evaluate("1-(2-(3+4))+3"));  //9
    }
}
