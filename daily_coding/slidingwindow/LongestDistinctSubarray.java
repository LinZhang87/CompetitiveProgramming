package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lzhang
 * @since 6/3/19
 */

/*
This problem was asked by Google.

Given an array of elements, return the length of the longest subarray where all its elements are distinct.

For example, given the array [5, 1, 3, 5, 2, 3, 4, 1], return 5 as the longest subarray of distinct elements is [5, 2, 3, 4, 1].

Sliding window algorithm: for each element nums[i], get the longest subarray of distinct elements that ends at nums[i].

Two cases when trying to append a new element nums[i]:

1. nums[i] has not appeared before, just add 1 to the length of the longest subarray of distinct elements that ends at nums[i];

2. nums[i] has appeared before, say the most occurrence happened at index j, then the length of the longest subarray of distinct
elements that ends at nums[i - 1] is (i - 1) - j + 1 = i - j.

A map is need to store the most recent occurrence indices, the result is the max of all subarrays out of all N options.

Don't forget to take the subarray that ends at the last element into consideration.

 */

public class LongestDistinctSubarray {
//    public static int longestDistinctSubarray(int[] nums) {
//        int left = 0, right = 0, maxLen = 0;
//        Set<Integer> seen = new HashSet<>();
//
//        while(right < nums.length) {
//            if(seen.contains(nums[right])) {
//                while(nums[left] != nums[right]) {
//                    seen.remove(nums[left]);
//                    left++;
//                }
//                seen.remove(nums[left]);
//                left++;
//            }
//            seen.add(nums[right]);
//            maxLen = Math.max(maxLen, right - left + 1);
//            right++;
//        }
//        return maxLen;
//    }

    public static int longestDistinctSubarray(int[] nums) {
        Map<Integer, Integer> mostRecentOccurence = new HashMap<>();
        int startIdx = 0, maxLen = 0;

        for(int i = 0; i < nums.length; i++) {
            if(mostRecentOccurence.containsKey(nums[i])) {
                int mostRecentIdx = mostRecentOccurence.get(nums[i]);
                if(mostRecentIdx >= startIdx) {
                    // i - 1 - startIdx + 1
                    maxLen = Math.max(maxLen, i - startIdx);
                    startIdx = mostRecentIdx + 1;
                }
            }
            mostRecentOccurence.put(nums[i], i);
        }
        maxLen = Math.max(maxLen, nums.length - startIdx);
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {5, 1, 3, 5, 2, 3, 4, 1};
        int[] nums1 = {1,1,1,1};
        System.out.println(longestDistinctSubarray(nums));
    }
}
