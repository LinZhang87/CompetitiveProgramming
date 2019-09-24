package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzhang
 * @since 8/5/19
 */

/*
The ancient Egyptians used to express fractions as a sum of several terms where each numerator is one.
For example, 4 / 13 can be represented as 1 / 4 + 1 / 18 + 1 / 468.

Create an algorithm to turn an ordinary fraction a / b, where a < b, into an Egyptian fraction.
 */

/*
Greedy algorithm: first find the greatest possible unit fraction, then recur for the remaining part.

For example, ceil of 13 / 4 is 4, so the 1st unit fraction is 1 / 4. Then recur on 4 / 13 - 1 / 4 =  3 / 52.
4 / 13 - 1 / 4 = 3 / 52;  (16 / 52 - 13 / 52)
3 / 52 - 1 / 18 = 2 / 936;
2 / 936 - 1 / 468 = 0.

The runtime is O(a). Each recursive calls reduces the nominator by 1 and the base case is when either nominator or
denominator is 0.
 */
public class EgyptianFraction {
    private static List<Integer> ans = new ArrayList<>();
    public static List<Integer> egyptianFraction(int a, int b) {
        efHelper(a, b);
        return ans;
    }
    private static void efHelper(int a, int b) {
        if(a == 0 || b == 0) {
            return;
        }
        if(b % a == 0) {
            ans.add(b / a);
            return;
        }
        else {
            int c = b / a + 1;
            ans.add(c);
            efHelper(a * c - b, b * c);
        }
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(egyptianFraction(12, 13).toArray(new Integer[0])));
    }
}
