package rangequeries.staticquery;

/**
 * @author lzhang
 * @since 3/13/20
 */

public class RangeMaxQuery_SparseTable {
    int n, k;
    int[] log;
    int[][] rangeMax;

    public RangeMaxQuery_SparseTable(int[] a) {
        n = a.length;
        log = new int[n + 1];
        log[1] = 0;
        //precompute log
        for(int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }
        k = log[n];
        rangeMax = new int[n][k + 1];
        for(int i = 0; i < n; i++) {
            rangeMax[i][0] = a[i];
        }
        //rangeMax[i][j] is the max in range[i, i + 2^j - 1] of length 2^j
        for(int j = 1; j <= k; j++) {
            for(int i = 0; i + (1 << j) <= n; i++) {
                rangeMax[i][j] = Math.max(rangeMax[i][j - 1], rangeMax[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    public int query(int L, int R) {
        //2^j is at least half the size of range [L, R], at most the entire size of range[L, R]
        int j = log[R - L + 1];
        return Math.max(rangeMax[L][j], rangeMax[R - (1 << j) + 1][j]);
    }
}
