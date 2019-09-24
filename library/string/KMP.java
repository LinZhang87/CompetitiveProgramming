package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzhang
 * @since 6/25/19
 */

/*
lps: longest proper prefix which is also suffix
 */

/*
Todo: Prove that the runtime of KMP is O(m + n)
 */
public class KMP {
    //find start index of first pattern, return -1 if none found
    public static int KMP_StringMatch(String text, String pattern) {
        //if pattern is "", return 0
        if(pattern.length() == 0) {
            return 0;
        }
        int[] lps = constructLps(pattern);
        int i = 0, j = 0;
        while(i < text.length() && j < pattern.length()) {
            if(text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            else {
                if(j > 0) {
                    j = lps[j - 1];
                }
                else {
                    i++;
                }
            }
        }
        if(j == pattern.length()) {
            return i - j;
        }
        return -1;
    }

    //find start indices of all pattern, return an empty list if none found
    public static List<Integer>  KMPAllMatches(String text, String pattern) {
        List<Integer> res = new ArrayList<>();
        //if pattern is "", return empty list
        if(pattern.length() == 0) {
            return res;
        }
        int[] lps = constructLps(pattern);
        int i = 0, j = 0;
        while(i < text.length()) {
            if(text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if(j == pattern.length()) {
                res.add(i - j);
                j = lps[j - 1];
            }
            else if(i < text.length() && text.charAt(i) != pattern.charAt(j)){
                if(j > 0) {
                    j = lps[j - 1];
                }
                else {
                    i++;
                }
            }
        }
        return res;
    }

    private static int[] constructLps(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        lps[0] = 0;
        int i = 0, j = 1;

        while(j < m) {
            if(pattern.charAt(j) == pattern.charAt(i)) {
                i++;
                lps[j] = i;
                j++;
            }
            else {
                if(i > 0) {
                    i = lps[i - 1];
                }
                else {
                    lps[j] = 0;
                    j++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructLps("AABAACAABAA")));
        System.out.println(Arrays.toString(KMPAllMatches("AABAACAADAABAABA", "AABA").toArray(new Integer[0])));
    }
}
