package math;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 9/10/19
 */

/*
The number 6174 is known as Kaprekar's contant, after the mathematician who discovered an associated property:
for all four-digit numbers with at least two distinct digits, repeatedly applying a simple procedure eventually results in this value.
The procedure is as follows:

For a given input x, create two new numbers that consist of the digits in x in ascending and descending order.
Subtract the smaller number from the larger number.
For example, this algorithm terminates in three steps when starting from 1234:

4321 - 1234 = 3087
8730 - 0378 = 8352
8532 - 2358 = 6174
Write a function that returns how many steps this will take for a given input N.
 */

public class KaprekarContant {
    private static final int KAPREKAR = 6174;
    public static int stepsToKaprekarContant(int N) {
        if(N < 1000 || N > 9999) {
            return -1;
        }
        int[] digits = generateDigits(N);
        if(digits[0] == digits[1] && digits[0] == digits[2] && digits[0] == digits[3]) {
            return -1;
        }
        int d = N, steps = 0;
        while (d != KAPREKAR) {
            int d1 = 0;
            for(int i = 3; i >= 0; i--) {
                d1 = d1 * 10 + digits[i];
            }
            int d2 = 0;
            for(int i = 0; i < 4; i++) {
                d2 = d2 * 10 + digits[i];
            }
            d = d1 - d2;
            digits = generateDigits(d);
            steps++;
        }
        return steps;
    }
    private static int[] generateDigits(int N) {
        int[] digits = new int[4];
        for(int i = 0; i < 4; i++) {
            digits[i] = N % 10;
            N = N / 10;
        }
        Arrays.sort(digits);
        return digits;
    }

    public static void main(String[] args) {
        System.out.println(stepsToKaprekarContant(1234));
    }
}
