package dp;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author lzhang
 * @since 6/2/19
 */

/*
    O(N^2) dp
 */
public class LongestIncreasingSubsequence {
    private int[] nums;
    private int[] lis;
    private int res;


    public LongestIncreasingSubsequence(int[] nums) {
        this.nums = Arrays.copyOf(nums, nums.length);
        lis = new int[nums.length];
        Arrays.fill(lis, 1);
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                }
            }
        }
        for(int i = 0; i < nums.length; i++) {
            res = Math.max(res, lis[i]);
        }
    }

    public int getLengthOfLIS() {
        return res;
    }

    public Integer[] reconstructLIS() {
        ArrayDeque<Integer> seq = new ArrayDeque();
        int i = lis.length - 1;
        int currLen = res, nextNum = Integer.MAX_VALUE;

        while(currLen > 0) {
            if(lis[i] == currLen && nums[i] < nextNum) {
                seq.addFirst(nums[i]);
                nextNum = nums[i];
                currLen--;
            }
            i--;
        }
        return seq.toArray(new Integer[0]);
    }

    public static void main(String[] args) {
        int[] nums = {5,3,2,4,6,1};
        LongestIncreasingSubsequence longestIncreasingSequence = new LongestIncreasingSubsequence(nums);
        System.out.println(longestIncreasingSequence.getLengthOfLIS());
        System.out.println(Arrays.toString(longestIncreasingSequence.reconstructLIS()));
    }
}
