package rangequeries.staticquery;

/**
 * @author lzhang
 * @since 3/13/20
 */

public class RangeMinQuery_SparseTable {
    int n, k;
    int[] log;
    int[][] rangeMin;

    public RangeMinQuery_SparseTable(int[] a) {
        n = a.length;
        log = new int[n + 1];   //log[i]: 2^log[i] = i; for i that is not 2^j, log[i] rounds down to the closest such 2^j that 2^j < i
        log[1] = 0; //2^0 = 1
        //precompute log
        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }
        k = log[n]; //if n is not 2^j, k is rounded down, so when initializing rangeMin we need to use k + 1
        rangeMin = new int[n][k + 1];
        for(int i = 0; i < n; i++) {
            rangeMin[i][0] = a[i];
        }
        //rangeMin[i][j] is the min in range[i, i + 2^j - 1] of length 2^j
        //loop over the range length 2^j first
        for(int j = 1; j <= k; j++) {
            //for each starting position a[i] if we have a subarray of length 2^j, compute rangeMin[i][j]
            for(int i = 0; i + (1 << j) <= n; i++) {
                rangeMin[i][j] = Math.min(rangeMin[i][j - 1], rangeMin[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    public int query(int L, int R) {
        //2^j is at least half the size of range [L, R], at most the entire size of range[L, R]
        int j = log[R - L + 1];
        return Math.min(rangeMin[L][j], rangeMin[R - (1 << j) + 1][j]);
    }
}
