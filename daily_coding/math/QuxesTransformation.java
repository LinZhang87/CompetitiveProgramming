package math;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author lzhang
 * @since 9/12/19
 */

/*
On a mysterious island there are creatures known as Quxes which come in three colors: red, green, and blue.
One power of the Qux is that if two of them are standing next to each other, they can transform into a single creature of the third color.

Given N Quxes standing in a line, determine the smallest number of them remaining after any possible sequence of such transformations.

For example, given the input ['R', 'G', 'B', 'G', 'B'], it is possible to end up with a single Qux through the following steps:

        Arrangement       |   Change
----------------------------------------
['R', 'G', 'B', 'G', 'B'] | (R, G) -> B
['B', 'B', 'G', 'B']      | (B, G) -> R
['B', 'R', 'B']           | (R, B) -> G
['B', 'G']                | (B, G) -> R
['R']                     |
 */

/*
Proof of correctness:
 */
public class QuxesTransformation {
    public static int minLeft(char[] quxes) {
        int red = 0, green = 0, blue = 0;
        for(char c : quxes) {
            switch (c) {
                case 'R':
                    red++;
                    break;
                case 'G':
                    green++;
                    break;
                case 'B':
                    blue++;
                    break;
            }
        }
        if(red == quxes.length || green == quxes.length || blue == quxes.length) {
            return quxes.length;
        }
        if(red % 2 == green % 2 && red % 2 == blue % 2) {
            return 2;
        }
        return 1;
    }
    public static void main(String[] args) {
        char[] quxes = {'B', 'R', 'G', 'G'};
        char[] quxes1 = {'R', 'R', 'R', 'R', 'B'};
        System.out.println(minLeft(quxes));
    }
}
