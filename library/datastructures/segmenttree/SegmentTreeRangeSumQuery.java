package datastructures.segmenttree;

/**
 * @author lzhang
 * @since 5/22/19
 */

/*
Range sum query: large number of queries: find a sum of all numbers of a subarray of a given array.

If the given array is constant, use prefix sum; If it is modified dynamically, use segment tree

Exercise: The sum can be replaced by minimum or maximum or some other operations with a few tricks.
 */

public class SegmentTreeRangeSumQuery {
    private static int expandedArraySize;
    private static long[] a;

    /*
     O(N) runtime, where N is the total number of segments, N = 2 * input.length, assuming input.length is a power of 2.
     Pad the input array if not.
      */
    public SegmentTreeRangeSumQuery(int[] input) {
        int x = (int) (Math.ceil(Math.log(input.length) / Math.log(2)));
        expandedArraySize = (int) Math.pow(2, x);
        int n = 2 * expandedArraySize;
        a = new long[n];

        //construct segment tree recursively using downward traversal
        //constructSegmentTreeUtil(input, 0, input.length - 1,1);
        //constructSegmentTreeUtil(input, 0,  expandedArraySize - 1,1);

        //construct segment tree using upward traversal
        //init the base case of segments of range 1
        for (int i = expandedArraySize; i < expandedArraySize * 2; i++) {
            if (i - expandedArraySize < input.length) {
                a[i] = input[i - expandedArraySize];
            }
        }
        for (int height = 4; height <= expandedArraySize; height = height * 2) {
            int start = n / height, len = start * 2;
            for (int i = start; i < len; i++) {
                a[i] = a[2 * i] + a[2 * i + 1];
            }
        }

        System.out.println();
    }

    /*
    @Params:
    input: the given input array;
    input_start, input_end: the start and end indices of the given input array;
    st_idx: the current index where the range sum needs to be calculated and stored in the segment tree;
     */
    private static long constructSegmentTreeUtil(int[] input, int input_start, int input_end, int st_idx) {
        if(input_start == input_end) {
            if(input_start < input.length) {
                a[st_idx] = input[input_start];
            }
            return a[st_idx];
        }
        int mid = input_start + (input_end - input_start) / 2;
        a[st_idx] = constructSegmentTreeUtil(input, input_start, mid, 2 * st_idx) +
                        constructSegmentTreeUtil(input, mid + 1, input_end, 2 * st_idx + 1);
        return a[st_idx];
    }

    /*
    O(logN) runtime
    @Params:
    l and r corresponds to a range of the input array
     */
    public long rangeSumQuery(int l, int r) {
        //offset [l ,r] by 1 as a[0] is not used
        l++;
        r++;
        return rangeSumQueryUtil(l, r, 1, expandedArraySize, 1);
    }

    private long rangeSumQueryUtil(int original_left, int original_right, int st_left, int st_right, int st_idx) {
        if(st_right < original_left || st_left > original_right) {
            return 0;
        }
        if(original_left <= st_left && original_right >= st_right) {
            return a[st_idx];
        }
        int mid = st_left + (st_right - st_left) / 2;
        return rangeSumQueryUtil(original_left, original_right, st_left, mid, st_idx * 2) +
                rangeSumQueryUtil(original_left, original_right, mid + 1, st_right, st_idx * 2 + 1);
    }

    /*
    O(logN) runtime
    @Params:
    idx is the index that needs to be updated in the original array
     */
    public void updateAtIndex(int idx, int newVal) {
        idx++;
        updateAtIndexUtil(idx, newVal, 1, expandedArraySize, 1);
    }

    private void updateAtIndexUtil(int idx, int newVal, int st_left, int st_right, int st_idx) {
        if(idx > st_right || idx < st_left) {
            return;
        }
        if(st_left == idx && st_right == idx) {
            a[st_idx] = newVal;
            return;
        }
        int mid = st_left + (st_right - st_left) / 2;
        updateAtIndexUtil(idx, newVal, st_left, mid, st_idx * 2);
        updateAtIndexUtil(idx, newVal, mid + 1, st_right, st_idx * 2 + 1);
        a[st_idx] = a[st_idx * 2] + a[st_idx * 2 + 1];
    }
    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        SegmentTreeRangeSumQuery st = new SegmentTreeRangeSumQuery(arr);

        System.out.println(st.rangeSumQuery(0, 5));

        st.updateAtIndex(2, 6);
        System.out.println(st.rangeSumQuery(0, 5));
    }
}
