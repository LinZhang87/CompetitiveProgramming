package binarysearch;

/**
 * @author lzhang
 * @since 12/4/19
 */

/*
    O(N log N) algorithm to compute the length of the longest increasing subsequence
 */
public class LISBinarySearch {
    /*
        This only correctly returns the LIS, the final values in sorted_nums is not necessarily a valid LIS.
     */
    public static int lengthOfLIS(int[] nums) {
        int[] sorted_nums = new int[nums.length];
        int currLen = 0;

        for(int v : nums) {
            int index = binarySearch(sorted_nums, 0, currLen - 1, v);
            if(index < 0) {
                sorted_nums[currLen] = v;
                currLen++;
            }
            else {
                sorted_nums[index] = v;
            }
        }
        return currLen;
    }
    //find the index of the left most number in sorted_nums that is >= target
    private static int binarySearch(int[] sorted_nums, int l, int r, int target) {
        if(l > r) {
            return -1;
        }
        //int len = r + 1;
        while(l < r - 1) {
            int mid = l + (r - l) / 2;
            if(sorted_nums[mid] >= target) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        if(sorted_nums[l] >= target) {
            return l;
        }
        else if(sorted_nums[r] >= target) {
            return r;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};
        lengthOfLIS(nums);
    }
}
