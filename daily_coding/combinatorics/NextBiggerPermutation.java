package combinatorics;

import java.util.Arrays;

/**
 * @author lzhang
 * @since 6/19/19
 */

/*
This problem was asked by IBM.

Given an integer, find the next permutation of it in absolute order. For example, given 48975, the next permutation would be 49578.

Signed integer max range: 2,147,483,647

if there is no bigger permutation, return the input integer itself

Pitfall: when finding the first adjacent digits that are decreasing order from right to left, digits[i - 1], digits[i].
swaping these two neighbors is not right.
You should swap digits[i - 1] with the smallest and rightmost digit that is just bigger than digits[i - 1] from digits[i, ...... digits.length - 1].

After this, digits[i, ...... digits.length - 1] is still in a decreasing sorted order, reverse it.
 */

public class NextBiggerPermutation {
    public static long nextBiggerPermutation(int num) {
        int[] digits = new int[10];
        Arrays.fill(digits, -1);
        int i = 9, d = num;
        while(d != 0) {
            digits[i] = d % 10;
            d /= 10;
            i--;
        }

        i = 9;
        while(i > 0 && digits[i] <= digits[i - 1]) {
            i--;
        }
        if(i == 0 || digits[i - 1] < 0) {
            return num;
        }

        //This logic is wrong
        int tmp = digits[i - 1];
        digits[i - 1] = digits[i];
        digits[i] = tmp;
        Arrays.sort(digits, i, digits.length);
        long res = 0;
        for(i = 0; i < digits.length; i++) {
            if(digits[i] >= 0) {
                res = res * 10 + digits[i];
            }
        }
        return res;
    }

    public static long nextBiggerPermutation2(int num) {
        int[] digits = new int[10];
        Arrays.fill(digits, -1);
        int i = 9, d = num;
        while(d != 0) {
            digits[i] = d % 10;
            d /= 10;
            i--;
        }

        i = 9;
        while(i > 0 && digits[i] <= digits[i - 1]) {
            i--;
        }
        if(i == 0 || digits[i - 1] < 0) {
            return num;
        }
        int nextBiggerIndex = binarySearchRightMostNextGreater(digits, i, digits.length - 1, digits[i - 1]);
        swap(digits, i - 1, nextBiggerIndex);

        int left = i, right = digits.length - 1;
        while(left < right) {
            swap(digits, left, right);
            left++;
            right--;
        }

        long res = 0;
        for(i = 0; i < digits.length; i++) {
            if(digits[i] >= 0) {
                res = res * 10 + digits[i];
            }
        }
        return res;

    }
    private static void swap(int[] digits, int i, int j) {
        int tmp = digits[i];
        digits[i] = digits[j];
        digits[j] = tmp;
    }
    private static int binarySearchRightMostNextGreater(int[] digits, int left, int right, int target) {
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(digits[mid] <= target) {
                right = mid - 1;
            }
            else {
                left = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(nextBiggerPermutation(48975));
        System.out.println(nextBiggerPermutation(12345));
        System.out.println(nextBiggerPermutation(54321));
        System.out.println(nextBiggerPermutation(44444));
        System.out.println(nextBiggerPermutation(469877554));

        System.out.println(nextBiggerPermutation2(48975));
        System.out.println(nextBiggerPermutation2(12345));
        System.out.println(nextBiggerPermutation2(54321));
        System.out.println(nextBiggerPermutation2(44444));
        System.out.println(nextBiggerPermutation2(469877554));
    }
}


