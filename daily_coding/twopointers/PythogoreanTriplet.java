package twopointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzhang
 * @since 9/4/19
 */

/*
Given an array of integers, determine whether it contains a Pythagorean triplet.
Recall that a Pythogorean triplet (a, b, c) is defined by the equation a2+ b2= c2.
 */
public class PythogoreanTriplet {
    public static boolean containsTriplet(int[] a) {
         long[] square = new long[a.length];

         //O(N)
         for(int i = 0; i < a.length; i++) {
             square[i] = ((long)a[i]) * a[i];
         }
         //NlogN
         Arrays.sort(square);

         //N^2 * logN
         for(int i = square.length - 1; i >= 2; i--) {
             for(int j = i - 1; j >= 1; j--) {
                 if(Arrays.binarySearch(square, 0, j, square[i] - square[j]) >= 0) {
                     return true;
                 }
             }
         }
         return false;
    }

    public static boolean containsTripletHashMap(int[] a) {
        long[] square = new long[a.length];
        Map<Long, Integer> count = new HashMap<>();
        //O(N)
        for(int i = 0; i < a.length; i++) {
            square[i] = ((long)a[i]) * a[i];
            count.put(square[i], count.getOrDefault(square[i], 0) + 1);
        }
        //NlogN
        Arrays.sort(square);

        //N^2
        for(int i = square.length - 1; i >= 2; i--) {
            for(int j = i - 1; j >= 1; j--) {
                count.put(square[i], count.get(square[i]) - 1);
                count.put(square[j], count.get(square[j]) - 1);
                if(count.getOrDefault(square[i] - square[j], 0) > 0) {
                    return true;
                }
                count.put(square[i], count.get(square[i]) + 1);
                count.put(square[j], count.get(square[j]) + 1);
            }
        }
        return false;
    }

    public static boolean containsTripletTwoPointer(int[] a) {
        long[] square = new long[a.length];

        //O(N)
        for(int i = 0; i < a.length; i++) {
            square[i] = ((long)a[i]) * a[i];
        }
        //NlogN
        Arrays.sort(square);

        //O(N^2)
        for(int i = square.length - 1; i >= 2; i--) {
            int j = 0, k = i - 1;
            while(j < k) {
                long sum = square[j] + square[k];
                if(sum == square[i]) {
                    return true;
                }
                else if(sum > square[i]) {
                    k--;
                }
                else {
                    j++;
                }
            }
        }
        return false;
    }
}
