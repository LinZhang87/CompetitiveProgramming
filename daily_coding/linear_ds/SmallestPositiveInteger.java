package linear_ds;

/**
 * @author lzhang
 * @since 7/8/19
 */

/*
This problem was asked by Amazon.

Given a sorted array, find the smallest positive integer that is not the sum of a subset of the array.

For example, for the input [1, 2, 3, 10], you should return 7.

Do this in O(N) time.
 */

/*
thought: assuming all elements in the sorted array are positive, then each time we process a new element, we
can compute the continuous interval that ends at the current element. Check if there is no gap between this interval and
the previous interval. if there is no gap, update the next possible missing number to be max of interval + 1; if there is gap, return the
previous interval's max + 1 as the answer.

Does this work on input that has negative elements?

 */

public class SmallestPositiveInteger {
    public static int smallestPositiveInteger(int[] a) {
        if(a == null || a.length == 0) {
            return 1;
        }
        int impossible_sum = 1;
        for(int i = 0; i < a.length; i++) {
            if(a[i] <= impossible_sum) {
                impossible_sum += a[i];
            }
            else {
                break;
            }
        }
        return impossible_sum;
    }
    public static int smallestPositiveInteger1(int[] a) {
        if(a == null || a.length == 0 || a[0] > 1) {
            return 1;
        }
        int prevRight = a[0];
        for(int i = 1; i < a.length; i++) {
            int currLeft = a[i], currRight = a[i] + prevRight;
            if(prevRight < currLeft - 1) {
                break;
            }
            else {
                prevRight = currRight;
            }
        }
        return prevRight + 1;
    }
    public static void main(String[] args) {
        int[] a1 = {1,2,3,10};  //ans: 7
        int[] a2 = {1,1,1,1};   //ans: 5
        //int[] a3 = {-4,-3,-2,-1,0,1,3}; //ans: 5
        int[] a4 = {1,3,4,5,6};    //ans : 2
        //int[] a5 = {-5,-3,-1,0,2,4,6};  //ans: 13
        int[] a6 = {0,0,1,2,3,10};
        System.out.println(smallestPositiveInteger(a1));
        System.out.println(smallestPositiveInteger(a2));
        //System.out.println(smallestPositiveInteger(a3));
        System.out.println(smallestPositiveInteger(a4));
        //System.out.println(smallestPositiveInteger(a5));
        System.out.println(smallestPositiveInteger(a6));

        System.out.println(smallestPositiveInteger1(a1));
        System.out.println(smallestPositiveInteger1(a2));
        //System.out.println(smallestPositiveInteger1(a3));
        System.out.println(smallestPositiveInteger1(a4));
        //System.out.println(smallestPositiveInteger1(a5));
        System.out.println(smallestPositiveInteger1(a6));
    }
}
