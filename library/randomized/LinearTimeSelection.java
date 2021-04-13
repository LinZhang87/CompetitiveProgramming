package randomized;

import java.util.Random;

/**
 * @author lzhang
 * @since 11/12/19
 */

/*
    Given a static array, find the kth smallest element from it.

    O(N) runtime on average.

    For dynamic array, augment balanced binary search tree
 */

public class LinearTimeSelection {
    private Random random = new Random();
    public int randSelect(int[] a, int k) {
        return selectHelper(a, 0, a.length - 1, k);
    }
    private int selectHelper(int[] a, int left, int right, int k) {
        if(left == right) {
            return a[left];
        }
        int p = partition(a, left, right);
        int rank = p - left + 1;    // p is the absolute index in the entire array a; rank is the relative statistic order in a[left, right], not the entire array!
        if(rank == k) {
            return a[p];
        }
        else if(rank > k) {
            return selectHelper(a, left, p - 1, k);
        }
        return selectHelper(a, p + 1, right, k - rank);
    }
    private int partition(int[] a, int left, int right) {
        if(left == right) {
            return left;
        }
        int partitionIdx = left + random.nextInt(right - left + 1);
        swap(a, left, partitionIdx);

        int i = left + 1;
        for(int j = left + 1; j <= right; j++) {
            if(a[j] < a[left]) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, left, i - 1);
        return i - 1;   // pivot's final index(0 based)
    }

    private void swap(int[] a, int i, int j) {
        if(i != j) {
            a[i] = a[i] ^ a[j];
            a[j] = a[i] ^ a[j];
            a[i] = a[i] ^ a[j];
        }
    }

    public static void main(String[] args) {
//        int[] a = {3,9,7,2,8,1,10,4,5,6};
        LinearTimeSelection linearTimeSelection = new LinearTimeSelection();
//        for(int i = 1; i <= 10; i++) {
//            System.out.println(linearTimeSelection.randSelect(a, i));
//        }

        int[] b = {3,9,7,2,8,1,10,4,5,6,1,2,3,4};
        for(int i = 1; i <= 10; i++) {
            System.out.println(linearTimeSelection.randSelect(b, i));
        }
    }
}
