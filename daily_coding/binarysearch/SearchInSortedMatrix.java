package binarysearch;

/**
 * @author lzhang
 * @since 6/10/19
 */

/*
This is your coding interview problem for today.

This problem was asked by Google.

Let A be an N by M matrix in which every row and every column is sorted.

Given i1, j1, i2, and j2, compute the number of elements of M smaller than M[i1, j1] and larger than M[i2, j2].

For example, given the following matrix:

[[1,  3,  7,   10,  15,  20],
 [2,  6,  9,   14,  22,  25],
 [3,  8,  10,  15,  25,  30],
 [10, 11, 12,  23,  30,  35],
 [20, 25, 30,  35,  40,  45]]

And i1 = 1, j1 = 1, i2 = 3, j2 = 3, return 15 as there are 15 numbers in the matrix smaller than 6 or greater than 23.
 */

public class SearchInSortedMatrix {

//    public static int countInRange(int[][] a, int i1, int j1, int i2, int j2) {
//        int M = a.length, N = a[0].length;
//        return countSmaller(a, 0, M - 1, 0, N - 1, a[i1][j1]) + countBigger(a, 0, M - 1, 0, N - 1, a[i2][j2]);
//    }
//    private static int countSmaller(int[][] a, int r1, int r2, int c1, int c2, int target) {
//        if(r1 > r2 || c1 > c2) {
//            return 0;
//        }
//        else if(r1 == r2 && c1 == c2) {
//            return a[r1][c1] < target ? 1 : 0;
//        }
//        int row_mid = r1 + (r2 - r1) / 2;
//        int col_mid = c1 + (c2 - c1) / 2;
//        if(a[row_mid][col_mid] >= target) {
//            return countSmaller(a, r1, row_mid - 1, c1, c2, target) + countSmaller(a, row_mid, r2, c1, col_mid - 1, target);
//        }
//        return (row_mid - r1 + 1) * (col_mid - c1 + 1) + countSmaller(a, row_mid + 1, r2, c1, c2, target) + countSmaller(a, r1, row_mid, col_mid + 1, c2, target);
//    }
//    private static int countBigger(int[][] a, int r1, int r2, int c1, int c2, int target) {
//        if(r1 > r2 || c1 > c2) {
//            return 0;
//        }
//        else if(r1 == r2 && c1 == c2) {
//            return a[r1][c1] > target ? 1 : 0;
//        }
//        int row_mid = r1 + (r2 - r1) / 2;
//        int col_mid = c1 + (c2 - c1) / 2;
//        if(a[row_mid][col_mid] <= target) {
//            return countBigger(a, row_mid + 1, r2, c1, c2, target) + countBigger(a, r1, row_mid, col_mid + 1, c2, target);
//        }
//        return (r2 - row_mid + 1) * (c2 - col_mid + 1) + countBigger(a, r1, row_mid - 1, c1, c2, target) + countBigger(a, row_mid, r2, c1, col_mid - 1, target);
//    }

    public static int countInRange(int[][] a, int i1, int j1, int i2, int j2) {
        int count = 0, row = 0, col = a[0].length - 1;

        //count numbers that are smaller than a[i1][j1]
        while(col >= 0) {
            while(row < a.length && a[row][col] < a[i1][j1]) {
                row++;
            }
            count += row;
            col--;
        }

        //count numbers that are bigger than a[i2][j2]
        row = a.length - 1;
        col = 0;
        while(col < a[0].length) {
            while(row >= 0 && a[row][col] > a[i2][j2]) {
                row--;
            }
            count += (a.length - 1 - row);
            col++;
        }
        return count;
    }
    public static void main(String[] args) {
        int[][] a = {{1, 3, 7, 10, 15, 20}, {2, 6, 9, 14, 22, 25},{3, 8, 10, 15, 25, 30},{10, 11, 12, 23, 30, 35},{20, 25, 30, 35, 40, 45}};
        System.out.println(countInRange(a, 1, 1, 3, 3));

        //System.out.println(countSmaller(a, 0, 1, 0, 5, 6));
        //System.out.println(countSmaller(a, 2, 4, 0, 1, 6));
//        System.out.println(countSmaller(a, 0, 1, 0, 1, 6));
//        System.out.println(countSmaller(a, 1, 1, 0, 1, 6));
//        System.out.println(countSmaller(a, 0, 0, 1, 1, 6));
    }
}
