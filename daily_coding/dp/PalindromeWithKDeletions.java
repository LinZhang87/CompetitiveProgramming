package dp;

/**
 * @author lzhang
 * @since 3/28/19
 */

/*
Daily Coding Problem 121

Given a string which we can delete at most k, return whether you can make a palindrome.

For example, given 'waterrfetawx' and a k of 2, you could delete f and x to get 'waterretaw'.
 */
public class PalindromeWithKDeletions {
//    public static boolean canMakePalindrome(String s, int k) {
//        int n = s.length();
//        boolean[][][] dp = new boolean[n][n][k + 1];
//        for(int i = 0; i < n; i++) {
//            for(int del = 0; del <= k; del++) {
//                dp[i][i][del] = true;
//            }
//        }
//        for(int startIdx = 0; startIdx <= n - 2; startIdx++) {
//            int endIdx = startIdx + 1;
//            for(int del = 0; del <= k; del++) {
//                if(del == 0 && s.charAt(startIdx) == s.charAt(endIdx) || del > 0) {
//                    dp[startIdx][endIdx][del] = true;
//                }
//            }
//        }
//        for(int del = 0; del <= k; del++) {
//            for(int l = 3; l <= n; l++) {
//                for(int startIdx = 0; startIdx <= n - l; startIdx++) {
//                    int endIdx = startIdx + l - 1;
//                    if(startIdx == 1 && endIdx == 9 && del == 1) {
//                        System.out.println();
//                    }
//                    if(s.charAt(startIdx) == s.charAt(endIdx)) {
//                        dp[startIdx][endIdx][del] = dp[startIdx + 1][endIdx - 1][del];
//                    }
//                    else if(del > 0) {
//                        dp[startIdx][endIdx][del] = (dp[startIdx + 1][endIdx][del - 1] || dp[startIdx][endIdx - 1][del - 1]);
//                    }
//                }
//            }
//        }
//        for(int del = 0; del <= k; del++) {
//            if(dp[0][n - 1][del]) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public boolean canMakePalindrome(String s, int k) {
//        return canMakePalindromeHelper(s, 0, s.length() - 1, k);
//    }
//
//    private boolean canMakePalindromeHelper(String s, int start, int end, int k) {
//        while(start < end) {
//            if(s.charAt(start) == s.charAt(end)) {
//                start++;
//                end--;
//            }
//            else {
//                break;
//            }
//        }
//        if(start == end) {
//            return true;
//        }
//        if(k == 0) {
//            return false;
//        }
//        return canMakePalindromeHelper(s, start + 1, end, k - 1) || canMakePalindromeHelper(s, start, end - 1, k - 1);
//    }

    public static boolean canMakePalindrome(String s, int k) {
        int longest = longestPalindromeSubseq(s);
        return s.length() - longest <= k;
    }

    private static int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            dp[i][i] = 1;
        }
        for(int i = 0; i < n - 1; i++){
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;
        }
        for(int l = 3; l <= n; l++){
            for(int startIdx = 0; startIdx <= n - l; startIdx++){
                int endIdx = startIdx + l - 1;
                if(s.charAt(startIdx) == s.charAt(endIdx)){
                    dp[startIdx][endIdx] = dp[startIdx + 1][endIdx - 1] + 2;
                }
                else{
                    dp[startIdx][endIdx] = Math.max(dp[startIdx + 1][endIdx], dp[startIdx][endIdx - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "waterrfetawx";
        System.out.println(canMakePalindrome(s, 3));
    }
}
