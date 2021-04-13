package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 8/28/19
 */

public class FibonacciSearch {
    public static int fibonacciSearch(int[] a, int target) {
        int n = a.length;
        Integer[] fibonacciSeq = fibonacciSequence(n);

        int offset = 0, p = fibonacciSeq.length - 2, q = fibonacciSeq.length - 1;
        while(q > 0) {
            int index = Math.min(offset + fibonacciSeq[p], n - 1);
            if(a[index] == target) {
                return index;
            }
            else if(a[index] > target) {
                p -= 2;
                q -= 2;
            }
            else {
                p -= 1;
                q -= 1;
                offset = index;
            }
        }
        return -1;
    }

    private static Integer[] fibonacciSequence(int n) {
        List<Integer> seq = new ArrayList<>();
        int a = 0, b = 1;
        seq.add(0);

        while(a < n) {
            int temp = a;
            a = b;
            b = temp + b;
            seq.add(a);
        }
        return seq.toArray(new Integer[0]);
    }
    public static void main(String[] args) {
        int[] a = {10,22,35,40,45,50,80,82,85,90,100};
        int[] b = new int[100];
        for(int i = 0; i < 100; i++) {
            b[i] = i;
        }
        System.out.println(fibonacciSearch(b, 50));
    }
}
