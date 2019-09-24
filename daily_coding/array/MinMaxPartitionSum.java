package array;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 7/27/19
 */

/*
This problem was asked by Etsy.

Given an array of numbers N and an integer k, your task is to split N into k partitions such that the maximum sum of any partition is minimized. Return this sum.

For example, given N = [5, 1, 2, 7, 3, 4] and k = 3, you should return 8, since the optimal partition is [5, 1, 2], [7], [3, 4].
 */

/*
Analysis: Intuitively, if the total sum is S, then we want each partition's sum be as close as to S / k as possible. So we can do a greedy algorithm as follows.




Dynamic Programming: Can you do it O(N * k) runtime iteratively?
dp[i][p]: the min max sum for nums[0, i] with p partitions;

init: dp[i][1] = prefixSum[i + 1]

ans: dp[0][k]
state:
 */
public class MinMaxPartitionSum {
//    public static int minMaxPartitionSum(int[] nums, int k) {
//        return minMaxPartitionSumBinarySearch(nums, k);
//    }

//    private static int minMaxPartitionSumDpIterative(int[] nums, int k) {
//        int[] prefixSum = new int[nums.length + 1];
//        for(int i = 1; i <= nums.length; i++) {
//            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
//        }
//        int[][] dp = new int[nums.length][k + 1];
//        for(int i = 0; i < nums.length; i++) {
//            Arrays.fill(dp[i], Integer.MAX_VALUE);
//
//        }
//        for(int i = 0; i < nums.length; i++) {
//            dp[i][1] = prefixSum[nums.length] - prefixSum[i];
//        }
//        return dp[0][k];
//    }

    private static int[][] dp;
    private static int[] prefixSum;
    public static int minMaxPartitionSumDpRecursion(int[] nums, int k) {
        prefixSum = new int[nums.length + 1];
        for(int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        dp = new int[nums.length][k + 1];
        for(int i = 0; i < nums.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for(int i = 0; i < nums.length; i++) {
            dp[i][1] = prefixSum[nums.length] - prefixSum[i];
        }
        return minMaxPartitionSumDpRecursionHelper(nums, 0, k);
    }
    private static int minMaxPartitionSumDpRecursionHelper(int[] nums, int startIdx, int partition) {
        if(dp[startIdx][partition] < Integer.MAX_VALUE) {
            return dp[startIdx][partition];
        }
        int minMaxPartitionSum = Integer.MAX_VALUE;
        for(int j = startIdx; j < nums.length + 1 - partition; j++) {
            minMaxPartitionSum = Math.min(minMaxPartitionSum,
                    Math.max(prefixSum[j + 1] - prefixSum[startIdx],minMaxPartitionSumDpRecursionHelper(nums,j + 1, partition - 1)));
        }
        dp[startIdx][partition] = minMaxPartitionSum;
        return minMaxPartitionSum;
    }

    public static int minMaxPartitionSumBinarySearch(int[] nums, int k) {
        int sum = 0, max = Integer.MIN_VALUE;
        for(int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        int left = Math.min(sum, max);
        int right = Math.max(sum, max);

        while(left < right - 1) {
            int mid = left + (right - left) / 2;
            if(check(nums, k, mid)) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return check(nums, k, left) ? left : right;
    }

    private static boolean check(int[] nums, int k, int limit) {
        int total = 0;
        int partitions = 1;

        for(int num : nums) {
            if(total + num > limit) {
                total = num;
                partitions++;
                if(partitions > k) {
                    return false;
                }
            }
            else {
                total += num;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] nums = {5,1,2,7,3,4};
        int[] nums1 = {1,2,3,10,11,12};
        int[] nums2 = {1,2};
        int[] nums3 = {-5,-1,-2,-7,-3,-4};
        int[] nums4 = {5, 1, 2, -7, 3, 4};
        System.out.println(minMaxPartitionSumDpRecursion(nums4, 3));
        System.out.println(minMaxPartitionSumBinarySearch(nums4, 3));
    }
}
