package datastructures.array;

/**
 * @author lzhang
 * @since 9/5/19
 */

/*
Kadane’s algorithm
 */
public class MaximumSubarraySum {

    /*
    Empty subarray of sum 0 is not considered.
     */
    public int maximumSubarraySumWithoutEmptySubarray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int maxSum = Integer.MIN_VALUE, currSum = 0, minSum = 0;
        for(int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            maxSum = Math.max(maxSum, currSum - minSum);
            minSum = Math.min(minSum, currSum);
        }
        return maxSum;
    }

    /*
    Emtpy subarray of sum 0 is considered.
     */
    public int maximumSubarrayWithEmptySubarray(int[] nums) {
        int best = 0, sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            best = Math.max(best, sum);
        }
        return best;
    }
}
