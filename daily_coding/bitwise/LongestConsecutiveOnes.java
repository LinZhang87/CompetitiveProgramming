package bitwise;

/**
 * @author lzhang
 * @since 6/28/19
 */

/*
This problem was asked by Stripe.

Given an integer n, return the length of the longest consecutive run of 1s in its binary representation.

For example, given 156, you should return 3.
 */

/*
Review:
Solution 1. longestConsecutiveOnes: you need to update the maxCount one more time after you exit the while loop for the
case that the longest consecutive ones happen starting from the MSB.

logic right shift >>> in java supports shift without considering the sign bit.
 */
public class LongestConsecutiveOnes {
    public static int longestConsecutiveOnes(int n) {
        int maxCount = 0, currCount = 0;
        while(n != 0) {
            if((n & 1) == 0) {
                maxCount = Math.max(maxCount, currCount);
                currCount = 0;
            }
            else {
                currCount++;
            }
            n = (n >>> 1);
            //System.out.println(Integer.toBinaryString(n));
        }
        maxCount = Math.max(maxCount, currCount);
        return maxCount;
    }

    public static int lcoBitwise(int n) {
        int maxCount = 0;
        while(n != 0) {
            maxCount++;
            //n = n & (n << 1);
            n = n & (n >>> 1);
        }
        return maxCount;
    }
    public static void main(String[] args) {
        System.out.println(longestConsecutiveOnes(-156));
        System.out.println(lcoBitwise(-156));
        System.out.println(Integer.toBinaryString(-156));
    }
}
