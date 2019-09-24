package bitwise;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzhang
 * @since 7/6/19
 */

/*
This problem was asked by Zillow.

Let's define a "sevenish" number to be one which is either a power of 7,
or the sum of unique powers of 7. The first few sevenish numbers are 1, 7, 8, 49, and so on.
Create an algorithm to find the nth sevenish number.
 */

public class Sevenish {
    public static long nthSevenishNumber(int n) {
        long res = 0, bit_place = 0;
        while(n != 0) {
            if((n & 1) != 0) {
                res += (long)Math.pow(7, bit_place);
            }
            n = (n >>> 1);
            bit_place++;
        }
        return res;
    }
    public static void main(String[] args) {

        System.out.println(nthSevenishNumber(1));
        System.out.println(nthSevenishNumber(2));
        System.out.println(nthSevenishNumber(3));
        System.out.println(nthSevenishNumber(4));
        System.out.println(nthSevenishNumber(10));
    }
}
