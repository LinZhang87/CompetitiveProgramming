package binarysearch;

/**
 * @author lzhang
 * @since 8/26/19
 */

/*
A fixed point in an array is an element whose value is equal to its index.
 Given a sorted array of distinct elements, return a fixed point, if one exists. Otherwise, return -1.

For example, given [-6, 0, 2, 40], you should return 2. Given [1, 5, 7, 8], you should return -1.


 */
public class FixedPoint {
    public static int fixedPoint(int[] a) {
        int l = 0, r = a.length - 1;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(a[mid] == mid) {
                return mid;
            }
            else if(a[mid] > mid) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return a[l] == l ? l : -1;
    }
    public static void main(String[] args) {
        int[] a = {1,5};
        System.out.println(fixedPoint(a));
    }
}
