package array;

/**
 * @author lzhang
 * @since 8/29/19
 */

/*
Write a function that rotates a list by k elements.
For example, [1, 2, 3, 4, 5, 6] rotated by two becomes [3, 4, 5, 6, 1, 2].
Try solving this without creating a copy of the list. How many swap or move operations do you need?
 */
public class LeftRotateArray {
    public static void leftRotateByK(int[] a, int k) {
        k = k % a.length;
        reverse(a, 0, k - 1);
        reverse(a, k, a.length - 1);
        reverse(a, 0, a.length - 1);
    }
    private static void reverse(int[] a, int i, int j) {
        while(i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
    }
    public static void main(String[] args) {
    }
}
