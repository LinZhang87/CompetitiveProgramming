package combinatorics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzhang
 * @since 6/20/19
 */

/*
This problem was asked by Twitter.

A permutation can be specified by an array P, where P[i] represents the location of the element at i
in the permutation. For example, [2, 1, 0] represents the permutation where elements at the index 0 and 2 are swapped.

Given an array and a permutation, apply the permutation to the array.
For example, given the array ["a", "b", "c"] and the permutation [2, 1, 0], return ["c", "b", "a"].
 */

public class PermutationMapping {

    public static void permutationMapping(String[] strings, int[] permutation) {
        Map<Integer, String> map = new HashMap<>();
        for(int i = 0; i < strings.length; i++) {
            map.put(i, strings[i]);
        }
        for(int i = 0; i < permutation.length; i++) {
            strings[i] = map.get(permutation[i]);
        }
    }

    public static void main(String[] args) {
        String[] strings = {"a", "b", "c", "d"};
        int[] permutation = {2,3,0,1};
        permutationMapping(strings, permutation);
        System.out.println(Arrays.toString(strings));
    }
}
