package array;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 8/15/19
 */

/*
This problem was asked by WhatsApp.

Given an array of integers out of order, determine the bounds of the smallest window
that must be sorted in order for the entire array to be sorted.
For example, given [3, 7, 5, 6, 9], you should return (1, 3).
 */
public class MinWindowToBeSorted {
    public static int[] minWindow(int[] a) {
        int[] window = new int[2];
        Arrays.fill(window, -1);
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i < a.length; i++) {
            if(a[i] < max) {
                window[1] = i;
            }
            else {
                max = a[i];
            }
        }
        for(int i = a.length - 1; i >= 0; i--) {
            if(a[i] > min) {
                window[0] = i;
            }
            else {
                min = a[i];
            }
        }
        return window;
    }
    public static void main(String[] args) {
        int[] a0 = {3,7,5,6,9};
        int[] a1 = {3,5,6,7,9};
        int[] a2 = {9,7,3,5,6};
        System.out.println(Arrays.toString(minWindow(a2)));
    }
}
