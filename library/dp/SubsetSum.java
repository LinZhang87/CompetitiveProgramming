package dp;

/**
 * @author lzhang
 * @since 7/9/19
 */

/*
    dp[i][j]: if there exists a subset from a[0, i] that sums to j
    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - a[i]]

    A special case is when target == 0; Depending on the requirement, we can consider an empty subset as a valid result or not.
    isSubsetSum1 does not consider empty set as valid answer;
    isSubsetSum2 considers empty set as valid answer.
 */
public class SubsetSum {
    public static boolean isSubsetSum1(int[] a, int target) {
        boolean[][] dp = new boolean[a.length][target + 1];
        for(int i = 0; i <= target; i++) {
            dp[0][i] = (a[0] == i);
        }
        if(dp[0][target]) {
            return true;
        }
        for(int i = 1; i < a.length; i++) {
            for(int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= a[i]) {
                    dp[i][j] |= dp[i - 1][j - a[i]];
                }
            }
            if(dp[i][target]) {
                return true;
            }
        }
        return false;
    }
    public static boolean isSubsetSum2(int[] a, int target) {
        boolean[][] dp = new boolean[a.length + 1][target + 1];
        for(int i = 0; i <= a.length; i++) {
            dp[i][0] = true;
        }
        for(int i = 1; i <= a.length; i++) {
            for(int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= a[i - 1]) {
                    dp[i][j] |= dp[i - 1][j - a[i - 1]];
                }
            }
            if(dp[i][target]) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] a1 = {3,5,2,1,8};
        int[] a2 = {5,2,4,1};
        System.out.println(isSubsetSum1(a1, 10));
        System.out.println(isSubsetSum1(a2, 0));
        System.out.println(isSubsetSum2(a1, 10));
        System.out.println(isSubsetSum2(a2, 0));
    }
}
