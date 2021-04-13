package datastructures.fenwicktree;

/**
 * @author lzhang
 * @since 2/28/20
 */

public class BinaryIndexedTree_2D {
    private int m;
    private int n;
    private int[][] tree;
    private int[][] num;

    /*
        O(M * N * log M * log N)
     */
    public BinaryIndexedTree_2D(int[][] matrix) {
        if(matrix.length > 0) {
            m = matrix.length;
            n = matrix[0].length;
            tree = new int[m + 1][n + 1];
            num = new int[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }
    }

    /*
        O(log M * log N)
     */
    public void update(int row, int col, int val) {
        int diff = val - num[row][col];
        num[row][col] = val;
        for(int i = row + 1; i <= m; i += (i & (-i))) {
            for(int j = col + 1; j <= n; j += (j & (-j))) {
                tree[i][j] += diff;
            }
        }
    }

    /*
        O(log M * log N)
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getRangeSum(row2 + 1, col2 + 1) - getRangeSum(row1, col2 + 1)
                - getRangeSum(row2 + 1, col1) + getRangeSum(row1, col1);
    }

    /*
        O(log M * log N)
     */
    private int getRangeSum(int x, int y) {
        int sum = 0;
        for(int i = x; i > 0; i -= (i & (-i))) {
            for(int j = y; j > 0; j -= (j & (-j))) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}
