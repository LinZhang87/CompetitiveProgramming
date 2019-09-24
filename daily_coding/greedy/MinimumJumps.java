package greedy;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 7/29/19
 */

/*
You are given an array of integers, where each element represents the maximum number of steps that can be jumped going forward from that element.
Write a function to return the minimum number of jumps you must take in order to get from the start to the end of the array.

For example, given [6, 2, 4, 0, 5, 1, 1, 4, 2, 9], you should return 2, as the optimal solution involves jumping from 6 to 5, and then from 5 to 9.
 */

/*
Dynamic programming:
dp[i]: the min jumps needed to get position i;

init: dp[0] = 0;
dp[i] = min {dp[j] + 1, for all j in [0, i) and j + jumps[j] >= i}

Greedy:
At any given position i,
next: the max position we can reach from positions[0, i)
curr: the previous max position we can reach before we have to make a jump

Iterate through the input array:
1. if next < i, it means there is no way to reach position i from position[0, i), return -1;
2. if curr < i, it means the previous max position we can reach does not cover i, we need to make a jump. Update curr to next
3. update next if needed after visiting each position
 */
public class MinimumJumps {
    public static int minJumpsDp(int[] jumps) {
        int n = jumps.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] < Integer.MAX_VALUE && j + jumps[j] >= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[j]);
                }
            }
            if(dp[i] == Integer.MAX_VALUE) {
                return -1;
            }
        }
        return dp[n - 1];
    }

    public static int minJumpsGreedy(int[] jumps) {
        int jumpUsed = 0, curr = 0, next = 0;

        for(int i = 0; i < jumps.length; i++) {
            //if next is < i, it means there is no way we can reach the end
            if(next < i) {
                return -1;
            }
            //if curr is < i, we need 1 more jump so we can reach position i. update curr to next
            if(curr < i) {
                jumpUsed++;
                curr = next;
            }
            next = Math.max(next, i + jumps[i]);
        }
        return jumpUsed;
    }
    public static void main(String[] args) {
        int[] jumps = {6, 2, 4, 0, 5, 1, 1, 4, 2, 9};
        int[] jumps1 = {6, 2, 4, 0, 2, 1, 1, 4, 2, 9};
        int[] jumps2 = {1,1,1,0,1,1};
        System.out.println(minJumpsDp(jumps2));
        System.out.println(minJumpsGreedy(jumps2));
    }
}
