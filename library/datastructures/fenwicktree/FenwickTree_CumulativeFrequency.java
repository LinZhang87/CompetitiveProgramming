package datastructures.fenwicktree;

/**
 * @author lzhang
 * @since 5/27/19
 */

/*
Fenwick tree, also called Binary indexed tree; A tree that is indexed by the bits of its integer keys.

Application: dynamic cumulative frequency table.


1-index based implementation:
input: an array of integer A, each integer A[i] represents A[i] appears one more time

 */
public class FenwickTree_CumulativeFrequency {
    private int[] ft;

    /*
        n is the largest integer value among all the input integers
     */
    public FenwickTree_CumulativeFrequency(int n) {
        ft = new int[n + 1];
    }

    /*
        query the sum in range [l, r]
     */
    public int rangeSumQuery(int l, int r) {
        return rangeSumQuery(r) - (l == 1 ? 0 : rangeSumQuery(l - 1));
    }

    /*
        query the sum in range[1, r]
     */
    private int rangeSumQuery(int r) {
        int sum = 0;
        for(; r > 0; r -= leastSignificantOne(r)) {
            sum += ft[r];
        }
        return sum;
    }

    /*
        adjust the value of index k by diff
     */
    public void adjust(int k, int diff) {
        for(; k < ft.length; k += leastSignificantOne(k)) {
            ft[k] += diff;
        }
    }

    private int leastSignificantOne(int i) {
        return i & (-i);
    }

    public static void main(String[] args) {
        int[] a = {2,4,5,5,6,6,6,7,7,8,9};
        FenwickTree_CumulativeFrequency ft = new FenwickTree_CumulativeFrequency(10);
        for(int i = 0; i < a.length; i++) {
            ft.adjust(a[i], 1);
        }
        System.out.println(ft.rangeSumQuery(1,1));
        System.out.println(ft.rangeSumQuery(1,2));
        System.out.println(ft.rangeSumQuery(1,6));
        System.out.println(ft.rangeSumQuery(1,10));
        System.out.println(ft.rangeSumQuery(3,6));

        ft.adjust(5, 2);
        System.out.println(ft.rangeSumQuery(1,10));
    }
}
