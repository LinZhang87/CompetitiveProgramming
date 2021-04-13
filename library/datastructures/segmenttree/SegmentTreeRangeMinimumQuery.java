package datastructures.segmenttree;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 5/30/19
 */

/*
We have an array arr[0 . . . n-1]. We should be able to efficiently find the minimum value from index qs (query start) to qe (query end) where 0 <= qs <= qe <= n-1.
 */

public class SegmentTreeRangeMinimumQuery {
    static class SegmentTreeRangeMinimumQueryRecursion {
        private int[] st;
        private int leafNodeNum;
        public SegmentTreeRangeMinimumQueryRecursion(int[] nums) {
            leafNodeNum = nums.length;
            int height = (int)(Math.ceil(Math.log(nums.length) / Math.log(2)));
            int max_size = (int)Math.pow(2, height) * 2 - 1;
            st = new int[max_size];
            Arrays.fill(st, Integer.MAX_VALUE);

            constructSegmentTreeUtil(nums, 0, nums.length - 1, 0);
        }

        private int constructSegmentTreeUtil(int[] nums, int nums_left, int nums_right, int st_idx) {
            if(nums_left == nums_right) {
                st[st_idx] = nums[nums_left];
            }
            else {
                int mid = nums_left + (nums_right - nums_left) / 2;
                int leftMin = constructSegmentTreeUtil(nums, nums_left, mid, st_idx * 2 + 1);
                int rightMin = constructSegmentTreeUtil(nums, mid + 1, nums_right, st_idx * 2 + 2);
                st[st_idx] = Math.min(leftMin, rightMin);
            }
            return st[st_idx];
        }

        /*
        @params: [left, right] is a range in the original input array nums
         */
        public int getMinimumInRange(int left, int right) {
            return getMinimumInRangeUtil(0, leafNodeNum - 1, 0, left, right);
        }

        /*
        @params: [st_left, st_right] is the a segment's covered range, at root, it is [0, leftNodeNum - 1];
                  st_idx is the index of the segment who covers the range [st_left, st_right];
                 [lr, rr] is the query range from the original input array nums
         */
        private int getMinimumInRangeUtil(int st_left, int st_right, int st_idx, int lr, int rr) {
            if(st_left > rr || st_right < lr) {
                return Integer.MAX_VALUE;
            }
            else if(lr <= st_left && rr >= st_right) {
                return st[st_idx];
            }
            int mid = st_left + (st_right - st_left) / 2;
            int leftMin = getMinimumInRangeUtil(st_left, mid, st_idx * 2 + 1, lr, rr);
            int rightMin = getMinimumInRangeUtil(mid + 1, st_right, st_idx * 2 + 2, lr, rr);
            return Math.min(leftMin, rightMin);
        }

        /*
        @params: idx is the index of the original array where the value should be updated
         */
        public void updateNumAtIndex(int idx, int newVal) {
            updateNumAtIndexUtil(0, leafNodeNum - 1, 0, idx, newVal);
        }

        private void updateNumAtIndexUtil(int st_left, int st_right, int st_idx, int idx, int newVal) {
            if(st_left <= idx && idx <= st_right) {
                if(st_left == st_right) {
                    st[st_idx] = newVal;
                }
                else {
                    int mid = st_left + (st_right - st_left) / 2;
                    updateNumAtIndexUtil(st_left, mid, st_idx * 2 + 1, idx, newVal);
                    updateNumAtIndexUtil(mid + 1, st_right, st_idx * 2 + 2, idx, newVal);
                    st[st_idx] = Math.min(st[st_idx * 2 + 1], st[st_idx * 2 + 2]);
                }
            }
        }
    }

    static class SegmentTreeRangeMinimumQueryIterative {
        private int[] st;
        private int leafNodeNum;
        private int expandedSize;
        private int height;

        public SegmentTreeRangeMinimumQueryIterative(int[] nums) {
            leafNodeNum = nums.length;
            height = (int)(Math.ceil(Math.log(nums.length) / Math.log(2)));
            expandedSize = (int)Math.pow(2, height);
            int max_size = expandedSize * 2 - 1;
            st = new int[max_size];

            for(int i = 0; i < expandedSize; i++) {
                st[expandedSize - 1 + i] = i >= leafNodeNum ? Integer.MAX_VALUE : nums[i];
            }
            for(int i = expandedSize - 2; i >= 0; i--) {
                st[i] = Math.min(st[i * 2 + 1], st[i * 2 + 2]);
            }
        }

        /*
        @params: [left, right] is a range in the original input array nums
        */
        public int getMinimumInRange(int left, int right) {
            // convert range of the original array to segment tree range
            left += (expandedSize - 1);
            right += (expandedSize - 1);

            int min = Integer.MAX_VALUE;

            while(left <= right) {
                if(left % 2 == 0) {
                    min = Math.min(min, st[left]);
                    left++;
                }
                if(right % 2 != 0) {
                    min = Math.min(min, st[right]);
                    right--;
                }
                left = (left - 1) / 2;
                right = (right - 2) /2;
            }
            return min;
        }

        public void updateNumAtIndex(int idx, int newVal) {
            idx += (expandedSize - 1);
            st[idx] = newVal;
            idx = idx % 2 != 0 ? (idx - 1) / 2 : (idx - 2) / 2;
            while(idx >= 0) {
                st[idx] = Math.min(st[idx * 2 + 1], st[idx * 2 + 2]);
                idx = idx % 2 != 0 ? (idx - 1) / 2 : (idx - 2) / 2;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,7,9,11};
        SegmentTreeRangeMinimumQuery.SegmentTreeRangeMinimumQueryRecursion stRecursion = new SegmentTreeRangeMinimumQuery.SegmentTreeRangeMinimumQueryRecursion(nums);

        SegmentTreeRangeMinimumQuery.SegmentTreeRangeMinimumQueryIterative stIterative = new SegmentTreeRangeMinimumQuery.SegmentTreeRangeMinimumQueryIterative(nums);

        System.out.println(stRecursion.getMinimumInRange(1, 5));
        System.out.println(stIterative.getMinimumInRange(1, 5));


        stRecursion.updateNumAtIndex(2, 0);
        stIterative.updateNumAtIndex(2, 0);

        System.out.println(stRecursion.getMinimumInRange(1, 5));
        System.out.println(stIterative.getMinimumInRange(1, 5));
    }
}
