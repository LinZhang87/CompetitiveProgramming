package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author lzhang
 * @since 9/5/19
 */

public class QuickSort {
    public void quickSort(int[] a) {
        Random random = new Random();
        quickSortHelper(a, 0,  a.length - 1, random);
    }
    private void quickSortHelper(int[] a, int left, int right, Random random) {
        if(left < right) {
            int randomPivotIdx = left + random.nextInt(right - left + 1);
            swap(a, left, randomPivotIdx);

            int partitionIdx = partition(a, left, right);
            quickSortHelper(a, left, partitionIdx - 1, random);
            quickSortHelper(a, partitionIdx + 1, right, random);
        }
    }
    private int partition(int[] a, int left, int right) {
        int pivotVal = a[left];
        int i = left + 1;

        //a[left + 1, i - 1]: partition that is < pivotVal; a[i, right]: partition that is >= pivotVal.
        for(int j = left + 1; j <= right; j++) {
            //If the current integer is < pivotVal, swap it to position i, then increment i by 1 to get the next available swapping position.
            if(a[j] < pivotVal) {
                swap(a, i, j);
                i++;
            }
        }
        //swap pivotVal a[left] to its correct sorting position
        swap(a, left, i - 1);
        return i - 1;
    }

    private void swap(int[] a, int i, int j) {
        if(i != j) {
            a[i] = a[i] ^ a[j];
            a[j] = a[i] ^ a[j];
            a[i] = a[i] ^ a[j];
        }
    }

    public static void main(String[] args) {
        int[] a = {7,4,8,2,5,1,0,3,9,5};
        int[] b = {7,4,8,2,5,1,0,4,7,5};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(b);
        System.out.println(Arrays.toString(b));
    }
}
