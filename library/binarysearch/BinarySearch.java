package binarysearch;

/**
 * @author lzhang
 * @since 3/8/20
 */

public class BinarySearch {
    /*
        Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
        If the target is not found in the array, return [-1, -1].
     */
    public static int[] searchRange(int[] nums, int target) {
        int first = firstPos(nums, target);
        //find the first index of target + 1, its previous is the last index of target
        int last = firstPos(nums, target + 1) - 1;
        if(first <= last) {
            return new int[]{first, last};
        }
        return new int[]{-1,-1};
    }
    private static int firstPos(int[] nums, int t) {
        int first = nums.length;
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] >= t) {
                first = mid;
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return first;
    }

    /*
        Given a sorted array A of unique integers and a target value, find the index of A that is the closest to target;
        if there are two answers, return the smaller index.
     */

    public static int closestToTarget(int[] p, int start, int end, int target) {
        int l = start, r = end;

        while(l < r - 1) {
            int mid = l + (r - l) / 2;
            //[l, mid - 1] can be excluded
            if(p[mid] <= target) {
                l = mid;
            }
            //[mid + 1, r] can be excluded
            else {
                r = mid;
            }
        }
        if(Math.abs(p[l] - target) <= Math.abs(p[r] - target)) {
            return l;
        }
        return r;
    }
}
