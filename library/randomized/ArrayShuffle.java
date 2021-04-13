package randomized;

import java.util.Arrays;
import java.util.Random;

/**
 * @author lzhang
 * @since 11/18/19
 */

/*
Fisher-Yates shuffle algorithm, O(N) time complexity

Start from the last element, swap it with a randomly selected element from the whole array(including last).
Now consider the array from 0 to n-2 (size reduced by 1), and repeat the process till we hit the first element.

 */
public class ArrayShuffle {
    public void shuffle(int[] a) {
        int n = a.length, i = n - 1;
        Random random = new Random();

        while(i >= 0) {
            int j = random.nextInt(i + 1);
            swap(a, i, j);
            i--;
        }
    }
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        ArrayShuffle arrayShuffle = new ArrayShuffle();
        arrayShuffle.shuffle(a);
        System.out.println(Arrays.toString(a));
    }
}
