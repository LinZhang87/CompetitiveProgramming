package dp;

/**
 * @author lzhang
 * @since 7/14/19
 */
/*
You are given N identical eggs and access to a building with k floors.
Your task is to find the lowest floor that will cause an egg to break, if dropped from that floor.
Once an egg breaks, it cannot be dropped again. If an egg breaks when dropped from the xth floor, you can assume it will also break when dropped from any floor greater than x.

Write an algorithm that finds the minimum number of trial drops it will take, in the worst case, to identify this floor.

For example, if N = 1 and k = 5, we will need to try dropping the egg at every floor, beginning with the first, until we reach the fifth floor, so our solution will be 5.
 */

/*
Let's start with dropping an egg from the mth floor. There are two possibilities:
Either, the egg breaks, in this case, the problem is reduced to a smaller problem: m - 1 floors with N - 1 eggs
Or, the egg does not break, int this case, the problem is reduced to another smaller problem: k - m floors with N eggs.

Dynamic programming is the right approach for this problem as you can see a simple recursion will result in redundant computations

dp[i][j]: the min number of trial drops needed in the worst case for i eggs and j floors
dp[i][j] = 1 + min of { Math.max(dp[i - 1][k - 1], dp[i][j - k], for m in [1, j] }

Initialization:
When there is 0 floors or 0 eggs, the min number of drops is 0 as there is nothing to try on;
When there is only 1 egg, then for j floors, the min drops in the worst case is j, as we need to drop from the 1st floor all the way
up to the jth floor, and it only breaks on the jth floor. This is the only scenario that we can find out the min floor that breaks the egg,
because we only have 1 egg. If it breaks in any other scenarios, we'll have no way to find the answer.

Answer: dp[N][k]
 */
public class MinEggDrops {
    public static int minEggDrops(int eggs, int floors) {
        int[][] dp = new int[eggs + 1][floors + 1];
        for(int j = 1; j <= floors; j++) {
            dp[1][j] = j;
        }
        for(int i = 2; i <= eggs; i++) {
            for(int j = 1; j <= floors; j++) {
                int min = Integer.MAX_VALUE;
                for(int k = 1; k <= j; k++) {
                    min = Math.min(min, Math.max(dp[i - 1][k - 1], dp[i][j - k]));
                }
                dp[i][j] = 1 + min;
            }
        }
        return dp[eggs][floors];
    }
    public static void main(String[] args) {
        System.out.println(minEggDrops(1, 5));
        System.out.println(minEggDrops(2, 10));
    }
}
