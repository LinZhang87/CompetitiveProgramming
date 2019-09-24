package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzhang
 * @since 9/20/19
 */

/*
A girl is walking along an apple orchard with a bag in each hand.
She likes to pick apples from each tree as she goes along, but is meticulous about not putting different kinds of apples in the same bag.

Given an input describing the types of apples she will pass on her path, in order,
determine the length of the longest portion of her path that consists of just two types of apple trees.

For example, given the input [2, 1, 2, 3, 3, 1, 3, 5], the longest portion will involve types 1 and 3, with a length of four.
 */

public class TypesOfApple {
    public static int longestPortionOfOnlyTwo(int[] apples) {
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0, r = 0, res = 0;
        while(r < apples.length) {
            map.put(apples[r], map.getOrDefault(apples[r] ,0) + 1);
            while(map.size() > 2) {
                map.put(apples[l], map.get(apples[l]) - 1);
                if(map.get(apples[l]) == 0) {
                    map.remove(apples[l]);
                }
                l++;
            }
            if(map.size() == 2) {
                res = Math.max(res, r - l + 1);
            }
            r++;
        }
        return res;
    }
    public static void main(String[] args) {
        int[] apples = {2, 1, 2, 3, 3, 1, 3, 5};
        System.out.println(longestPortionOfOnlyTwo(apples));
    }
}
