package array;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author lzhang
 * @since 8/13/19
 */

/*
The sequence [0, 1, ..., N] has been jumbled, and the only clue you have for its order is an array
representing whether each number is larger or smaller than the last. Given this information,
reconstruct an array that is consistent with it. For example, given [None, +, +, -, +], you could return [1, 2, 3, 0, 4].
 */
public class ReconstructSequence {
    public static int[] reconstructSequence(int N, String[] relations) {
        int count = 0;
        for(int i = 0; i <= N; i++) {
            if(relations[i].equals("-")) {
                count++;
            }
        }
        int incre = count, decre = count - 1;
        int[] res = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            if(relations[i].equals("-")) {
                res[i] = decre;
                decre--;
            }
            else {
                res[i] = incre;
                incre++;
            }
        }
        return res;
    }

//    public static int[] reconstructSequenceStack(int N, String[] relations) {
//        int[] res = new int[N + 1];
//        int idx = 0;
//        Stack<Integer> stack = new Stack<>();
//        for(int i = 0; i <= N; i++) {
//            if(relations[i + 1].equals("-")) {
//                stack.push(i + 1);
//            }
//            else {
//                res[idx] = i;
//                idx++;
//            }
//        }
//    }

    public static void main(String[] args) {
        //String[] relations = {"None", "+", "+", "+", "-", "+"};
        //String[] relations = {"None", "-", "-", "-", "-", "-"};
        String[] relations = {"None", "+", "-", "-", "-"};
        System.out.println(Arrays.toString(reconstructSequence(4, relations)));
    }
}
