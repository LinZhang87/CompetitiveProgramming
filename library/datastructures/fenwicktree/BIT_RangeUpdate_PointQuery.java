package datastructures.fenwicktree;

/**
 * @author lzhang
 * @since 2/29/20
 */

/*
A Fenwick tree can support the following range operations:

1    Point Update and Range Query
2    Range Update and Point Query
3    Range Update and Range Query

1 is just the ordinary Fenwick tree.

Using simple tricks we can support range updates and point queries.


 The element at index i is responsible for elements in the range [i-LSOne(i)+1..i] and
 ft[i] stores the sum of elements {i-LSOne(i)+1, i-LSOne(i)+2, i-LSOne(i)+3, .., i}

 */
public class BIT_RangeUpdate_PointQuery {
    private int[] ft;
    public BIT_RangeUpdate_PointQuery(int n) {
        ft = new int[n + 1];
    }

    /*
        add val in range [l, r]
        l and r are 1 based
     */
    public void rangeUpdate(int l, int r, int val) {
        add(l, val);
        add(r + 1, -val);
    }

    /*
        sum of [0, idx]
        idx is 0 based
     */
    public int pointQuery(int idx) {
        int sum = 0;
        for(++idx; idx > 0; idx -= (idx & (-idx))) {
            sum += ft[idx];
        }
        return sum;
    }

    /*
        idx: 0 based;
        add val to all ft[i] that covers idx
     */
    private void add(int idx, int val) {
        for(++idx; idx < ft.length; idx += (idx & (-idx))) {
            ft[idx] += val;
        }
    }
}
