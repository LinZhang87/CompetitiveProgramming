package datastructures.fenwicktree;

/**
 * @author lzhang
 * @since 2/29/20
 */

/*
    To support both range updates and range queries we will use two BITs.
 */
public class BIT_RangeUpdate_RangeQuery {
    private int[] ft1;
    private int[] ft2;

    public BIT_RangeUpdate_RangeQuery(int n) {
        ft1 = new int[n + 1];
        ft2 = new int[n + 1];
    }

    /*
        add val in range[l, r]
        l and r are 1 based
     */
    public void rangeUpdate(int l, int r, int val) {
        add(ft1, l, val);
        add(ft1, r + 1, -val);
        add(ft2, l, val * (l - 1));
        add(ft2, r + 1, -val * r);
    }

    /*
        l and r are 1 based
     */
    public int rangeSum(int l, int r) {
        return prefixSum(r) - prefixSum(l);
    }

    /*
        idx: 1 based;
     */
    private void add(int[] t, int idx, int val) {
        for(; idx < ft1.length; idx += (idx & (-idx))) {
            t[idx] += val;
        }
    }

    /*
        idx: 1 based;
     */
    private int prefixSum(int idx) {
        return sum(ft1, idx) * idx - sum(ft2, idx);
    }

    /*
        idx: 1 based;
     */
    private int sum(int[] t, int idx) {
        int total = 0;
        while(idx > 0) {
            total += t[idx];
            idx -= (idx & (-idx));
        }
        return total;
    }
}
