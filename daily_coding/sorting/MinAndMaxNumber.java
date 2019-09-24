package sorting;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 7/19/19
 */

/*
This problem was asked by Facebook.

Given an array of numbers of length N, find both the minimum and maximum using less than 2 * (N - 2) comparisons.
 */

/*
Linear scan uses 1 + 2 * (N - 2) comparisons in the worst case.

Tournament divide and conquer: split the array into left and right half, get their min/max respectively, then use 2
comparisons to get the min/max for the entire array

T(n) = 2T(n / 2) + 2; assuming n is a power of 2 for simplicity
T(n) = 2(2T(n/4) + 2) + 2 = 4T(n/4) + 2 + 4 = 4(2T(n/8) + 2) + 2 + 4; n / 2 * T(2) + 2 + 4 + 8 + ... + 2^k, k = logn - 1;
T(2) = 1, T(1) = 0;

T(n) = n / 2 + 2 * （1 - 2^3）/ (1 - 2) = n / 2 + (n / 2 - 1) * 2 = n / 2 + n - 2 = 3 * n / 2 - 2.

Why is divide and conquer cost fewer comparison? We eliminate all the unnecessary comparisons from the other half, only
compare the potential candidates from the other half. When doing a simple linear scan, we consider each number as a potential
candidate for both min and max, thus 2 comparisons for each number.
 */
public class MinAndMaxNumber {
    public static int[] getMinAndMax(int[] nums) {
        return divideAndConquer(nums, 0, nums.length - 1);
    }
    private static int[] divideAndConquer(int[] nums, int l, int r) {
        int[] res = new int[2];
        if(l == r) {
            res[0] = nums[l];
            res[1] = nums[r];
        }
        else if(l == r  - 1) {
            if(nums[l] >= nums[r]) {
                res[0] = nums[r];
                res[1] = nums[l];
            }
            else {
                res[0] = nums[l];
                res[1] = nums[r];
            }
        }
        else {
            int mid = l + (r - l) / 2;
            int[] leftRes = divideAndConquer(nums, l, mid);
            int[] rightRes = divideAndConquer(nums, mid + 1, r);
            res[0] = Math.min(leftRes[0], rightRes[0]);
            res[1] = Math.max(leftRes[1], rightRes[1]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        System.out.println(Arrays.toString(getMinAndMax(a)));
    }
}
