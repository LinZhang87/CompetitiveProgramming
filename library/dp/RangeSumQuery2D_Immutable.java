package dp;

/**
 * @author lzhang
 * @since 2/28/20
 */

public class RangeSumQuery2D_Immutable {
    private int[][] dp;

    public RangeSumQuery2D_Immutable(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = (j > 0 ? dp[i][j - 1] : 0) + (i > 0 ? dp[i - 1][j] : 0)
                        - (i > 0 && j > 0 ? dp[i - 1][j - 1] : 0) + matrix[i][j];
            }
        }
    }

    public int regionSum(int row1, int col1, int row2, int col2) {
        return dp[row2][col2] - (row1 > 0 ? dp[row1 - 1][col2] : 0)
                - (col1 > 0 ? dp[row2][col1 - 1] : 0) + (row1 > 0 && col1 > 0 ? dp[row1 - 1][col1 - 1] : 0);
    }

    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2},{5, 6, 3, 2, 1},{1, 2, 0, 1, 5},{4, 1, 0, 1, 7},{1, 0, 3, 0, 5}};
        RangeSumQuery2D_Immutable rsq = new RangeSumQuery2D_Immutable(matrix);
        int res = rsq.regionSum(2,1,4,3);
        System.out.println();
    }
}
