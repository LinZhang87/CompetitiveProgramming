package dp;

/**
 * @author lzhang
 * @since 6/4/19
 */

/*
This problem was asked by Facebook.

Given a circular array, compute its maximum subarray sum in O(n) time. A subarray can be empty, and in this case the sum is 0.

For example, given [8, -1, 3, 4], return 15 as we choose the numbers 3, 4, and 8 where the 8 is obtained from wrapping around.

Given [-4, 5, 1, 0], return 6 as we choose the numbers 5 and 1.

Follow up: what if a subarray can not be empty?

Changes need to make:
1. the max sum array should be length of N, not N + 1, as N + 1 contains the empty subarray case;

2. after finding the non-circular max sum, check if it is <= 0; if it is, it means we have the following 2 possible cases:

a). all numbers are negative;  b). there is at least 1 subarray whose elements are all 0s.

either case, simply return;

3. if the non-circular max sum is > 0, then we need to check circular max sum. To do this, we first find the non-circular
min sum, then subtract this min sum from the total sum. Since the non-circular max sum is > 0, it excludes the empty subarray case when
the min sum contains all elements.

 */

public class CircularArrayMaxSubarraySum {
//    public static int circularArrayMaxSubarraySum(int[] nums) {
//        int[] nonCircularMaxSum = new int[nums.length + 1];
//        int nonCircularMax = Integer.MIN_VALUE;
//        for(int i = 1; i <= nums.length; i++) {
//            nonCircularMaxSum[i] = Math.max(nonCircularMaxSum[i - 1] + nums[i - 1], nums[i - 1]);
//            nonCircularMax = Math.max(nonCircularMax, nonCircularMaxSum[i]);
//        }
//        int[] nonCircularMinSum = new int[nums.length + 1];
//        int nonCircularMin = Integer.MAX_VALUE, totalSum = 0;
//        for(int i = 1; i <= nums.length; i++) {
//            totalSum += nums[i - 1];
//            nonCircularMinSum[i] = Math.min(nonCircularMinSum[i - 1] + nums[i - 1], nums[i - 1]);
//            nonCircularMin = Math.min(nonCircularMin, nonCircularMinSum[i]);
//        }
//        return Math.max(nonCircularMax, totalSum - nonCircularMin);
//    }
//    public static void main(String[] args) {
//        int[] nums1 = {8,-1,3,4}, nums2 = {-4,5,-1,-1};
//
//        System.out.println(circularArrayMaxSubarraySum(nums1));
//        System.out.println(circularArrayMaxSubarraySum(nums2));
//    }

    //Follow up: what if a subarray can not be empty?
    public int maxSubarraySumCircular(int[] A) {
        int[] nonCircularMaxSum = new int[A.length];
        nonCircularMaxSum[0] = A[0];
        int nonCircularMax = nonCircularMaxSum[0];
        for(int i = 1; i < A.length; i++) {
            nonCircularMaxSum[i] = Math.max(nonCircularMaxSum[i - 1] + A[i], A[i]);
            nonCircularMax = Math.max(nonCircularMax, nonCircularMaxSum[i]);
        }
        if(nonCircularMax <= 0) {
            return nonCircularMax;
        }
        int[] nonCircularMinSum = new int[A.length + 1];
        int nonCircularMin = Integer.MAX_VALUE, totalSum = 0;
        for(int i = 1; i <= A.length; i++) {
            totalSum += A[i - 1];
            nonCircularMinSum[i] = Math.min(nonCircularMinSum[i - 1] + A[i - 1], A[i - 1]);
            nonCircularMin = Math.min(nonCircularMin, nonCircularMinSum[i]);
        }
        return Math.max(nonCircularMax, totalSum - nonCircularMin);
    }
}
