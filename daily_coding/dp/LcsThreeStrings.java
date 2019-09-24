package dp;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author lzhang
 * @since 6/24/19
 */

/*
This problem was asked by YouTube.

Write a program that computes the length of the longest common subsequence of three given strings.
For example, given "epidemiologist", "refrigeration", and "supercalifragilisticexpialodocious", it should return 5,
since the longest common subsequence is "eieio".
 */


public class LcsThreeStrings {
    private static int[][][] dp;
    public static int lcs(String s1, String s2, String s3) {
        int maxLen = 0;
        dp = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];
        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                for(int k = 1; k <= s3.length(); k++) {
                    if(s1.charAt(i - 1) == s2.charAt(j -1) && s1.charAt(i - 1) == s3.charAt(k - 1)) {
                        dp[i][j][k] = 1 + dp[i - 1][j - 1][k - 1];
                    }
//                    else if(s1.charAt(i - 1) == s2.charAt(j -1)) {
//                        dp[i][j][k] = Math.max(dp[i - 1][j - 1][k], dp[i][j][k - 1]);
//                    }
//                    else if(s1.charAt(i - 1) == s3.charAt(k -1)) {
//                        dp[i][j][k] = Math.max(dp[i - 1][j][k - 1], dp[i][j - 1][k]);
//                    }
//                    else if(s2.charAt(j - 1) == s3.charAt(k -1)) {
//                        dp[i][j][k] = Math.max(dp[i][j - 1][k - 1], dp[i - 1][j][k]);
//                    }
//                    else {
//                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
//                    }
                    else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                    maxLen = Math.max(maxLen, dp[i][j][k]);
                }
            }
        }
        return maxLen;
    }

    public static String constructLcs(String s1, String s2, String s3, int[][][] dp) {
        int maxLen = 0, x = -1, y = -1, z = -1;
        ArrayDeque<Character> dq = new ArrayDeque<>();

        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                for(int k = 1; k <= s3.length(); k++) {
                    if(dp[i][j][k] > maxLen && s1.charAt(i - 1) == s2.charAt(j - 1) && s1.charAt(i - 1) == s3.charAt(k - 1)) {
                        maxLen = dp[i][j][k];
                        x = i;
                        y = j;
                        z = k;
                    }
                }
            }
        }

        while(maxLen > 0) {
            if(s1.charAt(x - 1) == s2.charAt(y - 1) && s1.charAt(x - 1) == s3.charAt(z - 1)) {
                dq.addFirst(s1.charAt(x - 1));
                x--;
                y--;
                z--;
                maxLen--;
            }
            else if(dp[x - 1][y][z] >= dp[x][y - 1][z] && dp[x - 1][y][z] >= dp[x][y][z - 1]) {
                x--;
            }
            else if(dp[x][y - 1][z] >= dp[x - 1][y][z] && dp[x][y - 1][z] >= dp[x][y][z - 1]) {
                y--;
            }
            else {
                z--;
            }
        }
        //return String.valueOf(dq.toArray(new Character[0]));
        return Arrays.toString(dq.toArray(new Character[0]));
    }

    public static void main(String[] args) {
        String s1 = "epidemiologist", s2 = "refrigeration", s3 = "supercalifragilisticexpialodocious";
        lcs(s1, s2, s3);
        System.out.println(constructLcs(s1,s2,s3,dp));
    }

//    dp[i][j][k] = 1 + dp[i - 1][j - 1][k - 1], if s1[i] == s2[j] == s3[k];
//
//    else:
//
//    1. s1[i] == s2[j]: dp[i][j][k] = max of {dp[i - 1][j - 1][k], dp[i][j][k - 1]}
//    2. s1[i] == s3[k]: dp[i][j][k] = max of {dp[i - 1][j][k - 1], dp[i][j - 1][k]}
//    3. s2[j] == s3[k]: dp[i][j][k] = max of {dp[i][j - 1][k - 1], dp[i - 1][j][k]}
//    4. s1[i] != s2[j] != s3[k]: dp[i][j][k] = max of {dp[i - 1][j][k], dp[i][j - 1][k], dp[i][j][k - 1]}
}
