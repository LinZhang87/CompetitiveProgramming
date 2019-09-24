package sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 5/10/19
 */

/*
This problem was asked by Google.

Given an array of integers, return a new array where each element in the new array is the number of smaller elements to the right of that element in the original input array.

For example, given the array [3, 7, 9, 6, 1], return [1, 2, 2, 1, 0], since:

There is 1 smaller element to the right of 3
There is 2 smaller element to the right of 7
There are 2 smaller elements to the right of 9
There is 1 smaller element to the right of 6
There are no smaller elements to the right of 1

 */

public class NumberOfSmallerToRight {
    public static int[] numberOfSmallerToRight(int[] a) {
        if(a == null || a.length == 0) {
            return new int[] {};
        }
        int n = a.length;
        int[] count = new int[n];
        List<Integer> sortedList = new ArrayList<>();
        sortedList.add(a[n - 1]);

        for(int i = n - 2; i >= 0; i--) {
            count[i] = getSmallerCount(sortedList, a[i]);
            insertToSortedList(sortedList, a[i]);
        }
        return count;
    }
    private static int getSmallerCount(List<Integer> sortedList, int target) {
        int left = 0, right = sortedList.size() - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(sortedList.get(mid) >= target) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return sortedList.get(left) >= target ? left : sortedList.size();
    }
    private static void insertToSortedList(List<Integer> sortedList, int val) {
        int i = 0;
        while(i < sortedList.size()) {
            if(val <= sortedList.get(i)) {
                break;
            }
            i++;
        }
        sortedList.add(i, val);
    }
    public static void main(String[] args) {
        int[] a = {3,10,8,9,1,8,1};
        int[] b = {5,4,3,2,1};
        System.out.println(numberOfSmallerToRight(b));
    }
}
