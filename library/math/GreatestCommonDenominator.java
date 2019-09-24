package math;

/**
 * @author lzhang
 * @since 9/21/19
 */

public class GreatestCommonDenominator {

    //gcd between two integers a and b
    public static int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    //gcd among all the integers in array nums
    public static int gcd(int[] nums) {
        if(nums.length < 2) {
            return 0;
        }
        int gcd = gcd(nums[1], nums[0]);
        for(int i = 2; i < nums.length; i++) {
            gcd = gcd(nums[i], gcd);
        }
        return gcd;
    }
}
