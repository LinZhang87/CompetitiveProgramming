package sort;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 9/5/19
 */

/*
sorts an array in O(n) time assuming that every element in the array is an integer between 0...c and c=O(n).

Counting sort is a very efficient algorithm but it can only be used when the constant c is small enough,
so that the array elements can be used as indices in the bookkeeping array.

 */
public class CountingSort {
    public static void countingSort(int[] a) {
        int maxVal = 0;
        for(int v : a) {
            maxVal = Math.max(maxVal, v);
        }
        int[] freq = new int[maxVal + 1];
        for(int v : a) {
            freq[v]++;
        }
        int i = 0, j = 0;
        while(i < a.length) {
            Arrays.fill(a, i, i + freq[j], j);
            i += freq[j];
            j++;
        }
    }
    public static void main(String[] args) {
        int[] a = {1,3,6,9,9,3,5,9};
        countingSort(a);
        System.out.println(Arrays.toString(a));
    }
}
