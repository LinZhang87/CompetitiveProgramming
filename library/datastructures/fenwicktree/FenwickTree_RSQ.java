package datastructures.fenwicktree;

/**
 * @author lzhang
 * @since 11/18/19
 */

/*
    Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

    The update(i, val) function modifies nums by updating the element at index i to val.

    Example:

    Given nums = [1, 3, 5]

    sumRange(0, 2) -> 9
    update(1, 2)
    sumRange(0, 2) -> 8
    Note:

    The array is only modifiable by the update function.
    You may assume the number of calls to update and sumRange function is distributed evenly.

    In this application, nums' index i becomes integer key, nums[i] becomes the initial frequency of i. An update on nums[i]
    is equivalent with updating i's frequency. So we can just borrow the cumulative frequency implementation, just be mindful
    that it is a 1-index based implementation.
 */

public class FenwickTree_RSQ {
    private int[] nums;     // array that keeps the up-to-date array values after possible modifications
    private int[] ft;

    public FenwickTree_RSQ(int[] a) {
        nums = new int[a.length + 1];
        for(int i = 1; i <= a.length; i++) {
            nums[i] = a[i - 1];
        }
        ft = new int[a.length + 1];
        for(int i = 1; i < ft.length; i++) {
            adjust(i, nums[i]);
        }
    }

    /*
        query the sum in range [l, r], 1 index based
     */
    public int rangeSumQuery(int l, int r) {
        return rangeSumQuery(r) - (l == 1 ? 0 : rangeSumQuery(l - 1));
    }

    /*
        query the sum in range[1, r], 1 index based
     */
    private int rangeSumQuery(int r) {
        int sum = 0;
        for(; r > 0; r -= leastSignificantOne(r)) {
            sum += ft[r];
        }
        return sum;
    }

    /*
        adjust the value of index k to  v, 1 indexed based.
     */
    public void update(int k, int v) {
        adjust(k, v - nums[k]);
        //update nums[k] to have the newest value
        nums[k] = v;
    }

    private void adjust(int k, int diff) {
        for(; k < ft.length; k += leastSignificantOne(k)) {
            ft[k] += diff;
        }
    }

    private int leastSignificantOne(int i) {
        return i & (-i);
    }

    public static void main(String[] args) {
        int[] a = {1,3,5};
        FenwickTree_RSQ fenwickTree_rsq = new FenwickTree_RSQ(a);
        System.out.println(fenwickTree_rsq.rangeSumQuery(1, 3));    // 1 index
        fenwickTree_rsq.update(2, 2);
        System.out.println(fenwickTree_rsq.rangeSumQuery(1, 3));
    }
}
