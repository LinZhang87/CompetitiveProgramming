package binarysearch;

import java.util.*;

public class LISBinarySearchWithStack {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        //from left to right, main a list of stacks, each stack is non-increasing, i.e, we can only
        //push equal to or smaller than the current top value into a stack.
        //from left to right, all the stack tops are strictly increasing
        List<ArrayDeque<Integer>> qList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            //binary search to find the leftmost stack that we can push nums[i] into, create a new stack
            //on the rightmost side if necessary
            int idx = binarySearch(qList, nums[i]);
            if(idx == qList.size()) {
                qList.add(new ArrayDeque<>());
            }
            //get the correct stack and push nums[i] into it
            ArrayDeque<Integer> q = qList.get(idx);
            q.addFirst(nums[i]);
        }
        //the number of the stacks is the length of LIS; each top of each stacks from left to right form one longest increasing subsequence
        return qList.size();
    }
    private int binarySearch(List<ArrayDeque<Integer>> qList, int v) {
        if(qList.size() > 0) {
            int l = 0, r = qList.size() - 1;
            while(l < r) {
                int mid = l + (r - l) / 2;
                if(qList.get(mid).peekFirst() < v) {
                    l = mid + 1;
                }
                else {
                    r = mid;
                }
            }
            if(qList.get(l).peekFirst() >= v) {
                return l;
            }
        }
        return qList.size();
    }
}
