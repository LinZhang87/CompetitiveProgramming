package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzhang
 * @since 1/7/19
 */

public class SubsetSum {
    public static List<Integer> findSubsetSum(int[] a, int k) {
        if(a == null || a.length == 0) {
            return null;
        }
        Arrays.sort(a);
        if(a[0] > k) {
            return null;
        }
        boolean[][] T = new boolean[a.length + 1][k + 1];
        T[0][0] = true;
        for(int j = 1; j <= k; j++) {
            for(int i = 1; i <= a.length; i++) {
                if(j >= a[i - 1]) {
                    T[i][j] = T[i - 1][j - a[i - 1]];
                }
                T[i][j] |= T[i - 1][j];
            }
        }
        int idx = -1;
        for(int i = 1; i <= a.length; i++) {
            if(T[i][k]) {
                idx = i;
                break;
            }
        }
        if(idx < 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        int sum = k;
        while(sum != 0) {
            if(sum - a[idx - 1] >= 0 && T[idx - 1][sum - a[idx - 1]]) {
                result.add(a[idx - 1]);
                sum -= a[idx - 1];
            }

            idx--;
        }
        return result;
    }

    public static void main(String[] args) {
        int a[] = {1, 61, 5, 9, 2};
        List<Integer> result = findSubsetSum(a, 24);
        System.out.println("");
    }
}
