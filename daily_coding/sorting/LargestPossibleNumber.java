package sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lzhang
 * @since 7/12/19
 */

/*
This problem was asked by Twitter.

Given a list of numbers, create an algorithm that arranges them in order to form the largest possible integer.
For example, given [10, 7, 76, 415], you should return 77641510.
 */

/*
Algorithm: write a custom comparator that determines which number should appear first;

Some wrong solutions:
1. when comparing two numbers, if they do not have the same number of digits, simply pad the shorter number with its last
digits; Counter example: 513, 513444

2. or repeat the shorter number until it has the same length with the longer number, slicing off extra digits if needed
Counter example: [121,12]

Corner case: when all numbers are 0, then you should return 0 without other leading 0s.
 */

public class LargestPossibleNumber {
    public static String getLargestPossibleNumber(int[] nums) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        };
        String[] numStr = new String[nums.length];
        for(int i = 0; i < numStr.length; i++) {
            numStr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numStr, comparator);
        if(numStr[0].equals("0")) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for(String s : numStr) {
            res.append(s);
        }
        return res.toString();
    }
    public static void main(String[] args) {
        int[] nums = {10, 7, 76, 415};
        int[] nums1 = {513,513444};
        System.out.println(getLargestPossibleNumber(nums1));
    }
}
