package math;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 5/29/19
 */

/*
This problem was asked by Amazon.

Given n numbers, find the greatest common denominator between them.

For example, given the numbers [42, 56, 14], return 14.

Q: Can I assume all given numbers are positive integers?

 */

public class GreatestCommonDenominator {
//    public static int findGcd(int[] nums) {
//        int min = Integer.MAX_VALUE;
//        for(int num : nums) {
//            min = Math.min(min, num);
//        }
//        boolean flag = true;
//        int d = min;
//        for(; d >= 1; d--) {
//            for(int num : nums) {
//                if(num % d != 0) {
//                    flag = false;
//                    break;
//                }
//            }
//            if(flag) {
//                break;
//            }
//            else {
//                flag = true;
//            }
//        }
//        return d;
//    }
    public static int findGcd(int[] nums) {
        if(nums.length < 2) {
            return 0;
        }
        //Arrays.sort(nums);
        int gcd = gcdUtil(nums[1], nums[0]);

        for(int i = 2; i < nums.length; i++) {
            gcd = gcdUtil(nums[i], gcd);
        }
        return gcd;
    }
    private static int gcdUtil(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcdUtil(b, a % b);
    }
    public static void main(String[] args) {
        int[] nums = {8, 4, 6, 10};
        System.out.println(findGcd(nums));
    }
}
