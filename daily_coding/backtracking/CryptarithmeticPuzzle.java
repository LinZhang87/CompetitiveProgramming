package backtracking;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 8/3/19
 */

/*
A cryptarithmetic puzzle is a mathematical game where the digits of some numbers are represented by letters. Each letter represents a unique digit.

For example, a puzzle of the form:

  SEND
+ MORE
--------
 MONEY
may have the solution:

{'S': 9, 'E': 5, 'N': 6, 'D': 7, 'M': 1, 'O', 0, 'R': 8, 'Y': 2}
Given a three-word puzzle like the one above, create an algorithm that finds a solution.
 */

/*
Assume all letters are upper case English letters
 */
public class CryptarithmeticPuzzle {
    private static int[] res = new int[26];
    public static int[] playPuzzle(String word1, String word2, String word3) {
        Arrays.fill(res, -1);
        boolean[] used = new boolean[10];
        if(word3.length() > word1.length() && word3.length() > word2.length()) {
            res[word3.charAt(0) - 'A'] = 1;
            used[1] = true;
        }
        dfs(word1, word2, word3, word1.length() - 1, word2.length() - 1, word3.length() - 1, 0, used);
        return res;
    }

    private static boolean dfs(String w1, String w2, String w3, int i1, int i2, int i3, int carry, boolean[] used) {
        if(i3 < 0) {
            if(carry == 0) {
                return true;
            }
            else {
                return false;
            }
        }
        if(i1 >= 0 && res[w1.charAt(i1) - 'A'] < 0) {
            for(int d = 0; d <= 9; d++) {
                if(used[d]) {
                    continue;
                }
                res[w1.charAt(i1) - 'A'] = d;
                used[d] = true;
                if(dfs(w1, w2, w3, i1, i2, i3, carry, used)) {
                    return true;
                }
                else {
                    res[w1.charAt(i1) - 'A'] = -1;
                    used[d] = false;
                }
            }
            return false;
        }
        if(i2 >= 0 && res[w2.charAt(i2) - 'A'] < 0) {
            for(int d = 0; d <= 9; d++) {
                if(used[d]) {
                    continue;
                }
                res[w2.charAt(i2) - 'A'] = d;
                used[d] = true;
                if(dfs(w1, w2, w3, i1, i2, i3, carry, used)) {
                    return true;
                }
                else {
                    res[w2.charAt(i2) - 'A'] = -1;
                    used[d] = false;
                }
            }
            return false;
        }
        int sum = carry;
        sum += i1 >= 0 ? res[w1.charAt(i1) - 'A'] : 0;
        sum += i2 >= 0 ? res[w2.charAt(i2) - 'A'] : 0;
        if(res[w3.charAt(i3) - 'A'] >= 0 && sum % 10 == res[w3.charAt(i3) - 'A']) {
            return dfs(w1, w2, w3, i1 - 1, i2 - 1, i3 - 1, sum / 10, used);
        }
        else if(res[w3.charAt(i3) - 'A'] >= 0 || used[sum % 10]) {
            return false;
        }
        used[sum % 10] = true;
        res[w3.charAt(i3) - 'A'] = sum % 10;
        if(dfs(w1, w2, w3, i1 - 1, i2 - 1, i3 - 1, sum / 10, used)) {
            return true;
        }
        used[sum % 10] = false;
        res[w3.charAt(i3) - 'A'] = -1;
        return false;
    }

    public static void main(String[] args) {
        int[] res = playPuzzle("SEND", "MORE", "MONEY");
        System.out.println(Arrays.toString(res));
    }
}
