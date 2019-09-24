package greedy;

/**
 * @author lzhang
 * @since 6/6/19
 */

/*
This problem was asked by Google.

You are given an array of nonnegative integers. Let's say you start at the beginning of the array and are trying to
advance to the end. You can advance at most, the number of steps that you're currently on. Determine whether you can get to the end of the array.

For example, given the array [1, 3, 1, 2, 0, 1], we can go from indices 0 -> 1 -> 3 -> 5, so return true.

Given the array [1, 2, 1, 0, 0], we can't reach the end, so return false.
 */

/*
Greedy algorithm:

1. scan through arr and keep the current index currIdx and the maximum index canReachMaxIdx that can be reached so far.
2. If canReachMaxIdx >= arr.length - 1, then can reach;
   If the current index is already out of the reach of the canReachMaxIdx,it means we can not reach the current index, at this point, we should terminate and return false.
   Otherwise, update the canReachMaxIdx using the current index's steps
 */

public class CanReachEnd {

    public static boolean canReachEndDp(int[] arr) {
        boolean[] dp = new boolean[arr.length];
        dp[0] = true;

        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] |= (j + arr[j] >= i ? true : false);
                if(dp[i]) {
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }

    public static boolean canReachEndGreedy(int[] arr) {
        int canReachMaxIdx = 0;
        boolean canReach = false;
        for(int currIdx = 0; currIdx < arr.length; currIdx++) {
            if(canReachMaxIdx >= arr.length - 1) {
                canReach = true;
                break;
            }
            else if(currIdx > canReachMaxIdx) {
                break;
            }
            canReachMaxIdx = Math.max(canReachMaxIdx, currIdx + arr[currIdx]);
        }
        return canReach;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 3, 1, 2, 0, 1}, a2 = {1, 2, 1, 0, 0};
        assert canReachEndDp(a1) == true;
        assert canReachEndDp(a2) == false;

        assert canReachEndGreedy(a1) == true;
        assert canReachEndGreedy(a2) == false;
    }
}
