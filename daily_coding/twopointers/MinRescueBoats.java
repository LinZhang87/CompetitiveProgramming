package twopointers;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 9/13/19
 */

/*
An imminent hurricane threatens the coastal town of Codeville. If at most two people can fit in a rescue boat,
and the maximum weight limit for a given boat is k, determine how many boats will be needed to save everyone.

For example, given a population with weights [100, 200, 150, 80] and a boat limit of 200, the smallest number of boats required will be three.
 */
public class MinRescueBoats {
    public static int getMinNumber(int[] weights, int k) {
        int count = 0, left = 0, right = weights.length - 1;
        Arrays.sort(weights);
        while(left < right) {
            int diff = k - weights[right];
            if(weights[left] <= diff) {
                left++;
            }
            right--;
            count++;
        }
        if(left == right) {
            count++;
        }
        return count;
    }
}
