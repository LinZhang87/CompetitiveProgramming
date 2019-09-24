package simulation;

/**
 * @author lzhang
 * @since 6/30/19
 */

/*
This problem was asked by Facebook.

Given a number in Roman numeral format, convert it to decimal.

The values of Roman numerals are as follows:

{
    'M': 1000,
    'D': 500,
    'C': 100,
    'L': 50,
    'X': 10,
    'V': 5,
    'I': 1
}
In addition, note that the Roman numeral system uses subtractive notation for numbers such as IV and XL.

For the input XIV, for instance, you should return 14.
 */

public class RomanNumberConversion {
    public static int convertToDecimal(String s) {
        int[] map = new int[26];
        map['M' - 'A'] = 1000;
        map['D' - 'A'] = 500;
        map['C' - 'A'] = 100;
        map['L' - 'A'] = 50;
        map['X' - 'A'] = 10;
        map['V' - 'A'] = 5;
        map['I' - 'A'] = 1;

        int sum = 0;
        for(int i = 0; i < s.length(); i++) {
            sum += map[s.charAt(i) - 'A'];
            if(i > 0 && map[s.charAt(i) - 'A'] > map[s.charAt(i - 1) - 'A']) {
                sum -= 2 * map[s.charAt(i - 1) - 'A'];
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        //14
        System.out.println(convertToDecimal("XIV"));
        //1954
        System.out.println(convertToDecimal("MCMLIV"));
        //2019
        System.out.println(convertToDecimal("MMXIX"));
        //1776
        System.out.println(convertToDecimal("MDCCLXXVI"));
    }
}
