package dp;

/**
 * @author lzhang
 * @since 10/25/19
 */

/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */


public class MaximumProductSubarray {
    public static int maxProduct(int[] arr) {
        int n = arr.length;
        int minVal = arr[0], maxVal = arr[0], maxProduct = arr[0];

        for (int i = 1; i < n; i++)  {
            if (arr[i] < 0) {
                int temp = maxVal;
                maxVal = minVal;
                minVal =temp;
            }
            maxVal = Math.max(arr[i], maxVal * arr[i]);
            minVal = Math.min(arr[i], minVal * arr[i]);

            maxProduct = Math.max(maxProduct, maxVal);
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        int[] a = {2,3,-2,4-3,-2,0,2};
        maxProduct(a);
    }
}
